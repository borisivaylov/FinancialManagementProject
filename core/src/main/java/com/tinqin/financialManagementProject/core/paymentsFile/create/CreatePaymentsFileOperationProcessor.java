package com.tinqin.financialManagementProject.core.paymentsFile.create;

import com.tinqin.accountingproject.api.invoice.GetByPaymentDate.GetByPaymentDateRequest;
import com.tinqin.accountingproject.api.invoice.GetByPaymentDate.GetByPaymentDateResponse;
import com.tinqin.accountingproject.api.invoice.getById.GetByInvoiceIdResponse;
import com.tinqin.accountingproject.persistence.entity.Invoice;
import com.tinqin.accountingproject.restExport.AccountingRestExport;
import com.tinqin.financialManagementProject.api.paymentUnit.create.CreatePaymentUnitRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileResponse;
import com.tinqin.financialManagementProject.core.paymentUnit.create.CreatePaymentUnitOperationProcessor;
import com.tinqin.financialManagementProject.persistence.entity.PaymentUnit;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePaymentsFileOperationProcessor implements CreatePaymentsFileOperation {

    private final PaymentFileRepository paymentFileRepository;
    private final AccountingRestExport accountingRestExport;
    private final CreatePaymentUnitOperationProcessor createPaymentUnitOperationProcessor;

    @Override
    public CreatePaymentsFileResponse process(CreatePaymentsFileRequest operationInput) {

        List<GetByPaymentDateResponse> getInvocesByPaymentDateResponseList = accountingRestExport.getByPaymentDate(
                GetByPaymentDateRequest.builder().
                        dateOfPayment(operationInput.getDateOfPayment()).build());
        List<String> invoicesIdList = new ArrayList<>();
        List<PaymentUnit>paymentUnitList = new ArrayList<>();


        if (getInvocesByPaymentDateResponseList.isEmpty()){
            throw new NoSuchElementException("There are no invoices to be paid on this date: "
                    + operationInput.getDateOfPayment());
        }


        for (GetByPaymentDateResponse invoiceResponse: getInvocesByPaymentDateResponseList) {
            invoicesIdList.add(invoiceResponse.getInvoiceNumber());
        }



        PaymentsFile paymentsFile  = paymentFileRepository.save(PaymentsFile.builder()
                .invoicesForPayment(invoicesIdList)
                .Employee("Elena Trifonova")
                .dateOfCreation(operationInput.getDateOfPayment())
                .sent(false)
                .build());


        System.out.println(paymentsFile.getUuid().toString());

        for (String invoiceId:invoicesIdList) {

            GetByInvoiceIdResponse invoice = accountingRestExport.getInvoiceById(invoiceId);

             createPaymentUnitOperationProcessor.process(CreatePaymentUnitRequest
                    .builder()
                            .paymentFileId(paymentsFile.getUuid())
                            .invoiceNumber(invoiceId)
                            .invoiceCreatorIBAN(invoice.getCreatorProviderIBAN())
                            .invoiceCreatorName(invoice.getCreatorProviderId().toString())
                            .receiverName(invoice.getReceiverProviderId().toString())
                            .invoiceReceiverIBAN(invoice.getReceiverProviderIBAN())
                            .paymentSum(invoice.getSum())
                    .build());

            paymentUnitList.add(PaymentUnit.builder()
                    .paymentFileUuid(paymentsFile.getUuid())
                    .invoiceId(invoiceId)
                    .invoiceCreatorIBAN(invoice.getCreatorProviderIBAN())
                    .invoiceCreatorName(invoice.getCreatorProviderId().toString())
                    .invoiceReceiverName(invoice.getReceiverProviderId().toString())
                    .invoiceReceiverIBAN(invoice.getReceiverProviderIBAN())
                    .sum(invoice.getSum())
                    .build());
        }

        System.out.println(paymentsFile.getUuid());

        paymentsFile.setPaymentUnitList(paymentUnitList);

        paymentFileRepository.save(paymentsFile);

        return CreatePaymentsFileResponse.builder()
                .paymentFileID(paymentsFile.getUuid())
                .paymentUnitList(paymentsFile.getPaymentUnitList())
                .invoicesForPayment(paymentsFile.getInvoicesForPayment())
                .dateOfCreation(paymentsFile.getDateOfCreation())
                .Employee(paymentsFile.getEmployee())
                .sent(paymentsFile.isSent())
                .build();
    }
}
