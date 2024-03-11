package com.tinqin.financialManagementProject.core.paymentsFile.update;

import com.tinqin.financialManagementProject.api.paymentsFile.update.UpdatePaymentsFileOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.update.UpdatePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.update.UpdatePaymentsFileResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UpdatePaymentsFileOperationProcessor implements UpdatePaymentsFileOperation {

    private final PaymentFileRepository paymentFileRepository;

    @Override
    public UpdatePaymentsFileResponse process(UpdatePaymentsFileRequest operationInput) {

        PaymentsFile paymentsFile = paymentFileRepository.findById(operationInput.getPaymentFileID())
                .orElseThrow(()-> new NoSuchElementException("There is no such payments file."));

        paymentsFile.setDateOfCreation(operationInput.getDateOfCreation());
        paymentsFile.setEmployee(operationInput.getEmployee());

        paymentFileRepository.save(paymentsFile);


        return UpdatePaymentsFileResponse.builder()
                .paymentFileID(paymentsFile.getUuid())
                .invoicesForPayment(paymentsFile.getInvoicesForPayment())
                .dateOfCreation(paymentsFile.getDateOfCreation())
                .Employee(paymentsFile.getEmployee())
                .sent(paymentsFile.isSent())
                .status("Payments File " + paymentsFile.getUuid() +" successfully updated.")

                .build();
    }
}
