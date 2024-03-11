package com.tinqin.financialManagementProject.core.paymentsFile.create;

import com.tinqin.accountingproject.api.invoice.GetByPaymentDate.GetByPaymentDateRequest;
import com.tinqin.accountingproject.api.invoice.GetByPaymentDate.GetByPaymentDateResponse;
import com.tinqin.accountingproject.restExport.AccountingRestExport;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CreatePaymentsFileOperationProcessor implements CreatePaymentsFileOperation {

    private final PaymentFileRepository paymentFileRepository;
    private final AccountingRestExport accountingRestExport;


    @Override
    public CreatePaymentsFileResponse process(CreatePaymentsFileRequest operationInput) {

        List<GetByPaymentDateResponse> getInvocesByPaymentDateResponseList = accountingRestExport.getByPaymentDate(
                GetByPaymentDateRequest.builder().
                        dateOfPayment(operationInput.getDateOfPayment()).build());

        if (getInvocesByPaymentDateResponseList.isEmpty()){
            throw new NoSuchElementException("There are no invoices to be paid on this date: "
                    + operationInput.getDateOfPayment());
        }

        List<String> invoicesIdList = new ArrayList<>();

        for (GetByPaymentDateResponse invoiceResponse: getInvocesByPaymentDateResponseList) {
            invoicesIdList.add(invoiceResponse.getInvoiceNumber());
        }

        PaymentsFile paymentsFile = PaymentsFile.builder()
                .invoicesForPayment(invoicesIdList)
                .dateOfCreation(operationInput.getDateOfPayment())
                .Employee("ddz")
                .sent(false)
                .build();

        paymentFileRepository.save(paymentsFile);

        return CreatePaymentsFileResponse.builder()
                .paymentFileID(paymentsFile.getUuid())
                .invoicesForPayment(paymentsFile.getInvoicesForPayment())
                .dateOfCreation(paymentsFile.getDateOfCreation())
                .Employee(paymentsFile.getEmployee())
                .sent(paymentsFile.isSent())
                .build();
    }
}
