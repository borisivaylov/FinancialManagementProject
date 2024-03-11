package com.tinqin.financialManagementProject.core.paymentsFile.addInvoiceForPayment;

import com.tinqin.accountingproject.api.invoice.getById.GetByInvoiceIdResponse;
import com.tinqin.accountingproject.persistence.entity.Invoice;
import com.tinqin.accountingproject.persistence.repository.InvoiceRepository;
import com.tinqin.accountingproject.restExport.AccountingRestExport;
import com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment.AddInvoiceForPaymentOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment.AddInvoiceForPaymentRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment.AddInvoiceForPaymentResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddInvoiceForPaymentOperationProcessor implements AddInvoiceForPaymentOperation {

    private final PaymentFileRepository paymentFileRepository;
    private final AccountingRestExport accountingRestExport;
    @Override
    public AddInvoiceForPaymentResponse process(AddInvoiceForPaymentRequest operationInput) {

        PaymentsFile paymentsFile = paymentFileRepository.findById(operationInput.getPaymentFileId())
                .orElseThrow(() -> new NoSuchElementException("There is no such payments file."));

        GetByInvoiceIdResponse invoice = accountingRestExport.getInvoiceById(operationInput.getInvoiceId());


        if (paymentsFile.getInvoicesForPayment().contains(operationInput.getInvoiceId())) {
            throw new IllegalArgumentException("Invoice with number " + invoice.getInvoiceNumber() +
                    " is already in payment file " + paymentsFile.getUuid());}


            paymentsFile.getInvoicesForPayment().add(invoice.getInvoiceNumber());
            paymentFileRepository.save(paymentsFile);

            return AddInvoiceForPaymentResponse.builder()
                    .paymentFileID(paymentsFile.getUuid())
                    .invoicesForPayment(paymentsFile.getInvoicesForPayment())
                    .dateOfCreation(paymentsFile.getDateOfCreation())
                    .Employee(paymentsFile.getEmployee())
                    .sent(paymentsFile.isSent())
                    .status("Invoice " + invoice.getInvoiceNumber() + " successfully added for payment in file "
                            + paymentsFile.getUuid())
                    .build();
        }
    }
