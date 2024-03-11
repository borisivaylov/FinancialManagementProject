package com.tinqin.financialManagementProject.core.paymentsFile.delete;


import com.tinqin.financialManagementProject.api.paymentsFile.delete.DeletePaymentsFileOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.delete.DeletePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.delete.DeletePaymentsFileResponse;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePaymentsFileOperationProcessor implements DeletePaymentsFileOperation {

    private final PaymentFileRepository paymentFileRepository;
    @Override
    public DeletePaymentsFileResponse process(DeletePaymentsFileRequest operationInput) {

        if(!(paymentFileRepository.existsById(operationInput.getPaymentFileId())))
        {
            return DeletePaymentsFileResponse.builder()
                    .status("There is no such payments file with id "+ operationInput.getPaymentFileId())
                    .build();
        }

        paymentFileRepository.deleteById(operationInput.getPaymentFileId());


        return DeletePaymentsFileResponse.builder()
                .status("Successfully deleted payments file with id "+ operationInput.getPaymentFileId())
                .build();
    }
}
