package com.tinqin.financialManagementProject.core.paymentsFile.removeInvoiceforPayment;

import com.tinqin.financialManagementProject.api.paymentsFile.removeInvoiceForPayment.RemoveInvoiceForPaymentOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.removeInvoiceForPayment.RemoveInvoiceForPaymentRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.removeInvoiceForPayment.RemoveInvoiceForPaymentResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RemoveInvoiceForPaymentOperationProcessor implements RemoveInvoiceForPaymentOperation {

    private final PaymentFileRepository paymentFileRepository;
    @Override
    public RemoveInvoiceForPaymentResponse process(RemoveInvoiceForPaymentRequest operationInput) {

        PaymentsFile paymentsFile = paymentFileRepository.findById(operationInput.getPaymentFileId())
                .orElseThrow(()-> new NoSuchElementException("There is no such payments file."));


            if (!(paymentsFile.getInvoicesForPayment().contains(operationInput.getInvoiceId()))){
                throw new IllegalArgumentException("Invoice with number "+  operationInput.getInvoiceId()+
                        " is not in payment file " + paymentsFile.getUuid());
            }

        paymentsFile.getInvoicesForPayment().remove(operationInput.getInvoiceId());

        paymentFileRepository.save(paymentsFile);

        return RemoveInvoiceForPaymentResponse.builder()
                .paymentFileID(paymentsFile.getUuid())
                .invoicesForPayment(paymentsFile.getInvoicesForPayment())
                .dateOfCreation(paymentsFile.getDateOfCreation())
                .Employee(paymentsFile.getEmployee())
                .sent(paymentsFile.isSent())
                .status("Invoice" + operationInput.getInvoiceId() + " successfully removed for payment in file " + paymentsFile.getUuid())
                .build();
    }
}
