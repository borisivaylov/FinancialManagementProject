package com.tinqin.financialManagementProject.core.paymentsFile.getByPaymentDate;

import com.tinqin.financialManagementProject.api.paymentsFile.getByPaymentDate.GetByPaymentFileByDateOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.getByPaymentDate.GetByPaymentFileByDateRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.getByPaymentDate.GetByPaymentFileByDateResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetPaymentsFileByDateOperationProcessor implements GetByPaymentFileByDateOperation {

    private final PaymentFileRepository paymentFileRepository;
    @Override
    public List<GetByPaymentFileByDateResponse> process(GetByPaymentFileByDateRequest operationInput) {

        List<PaymentsFile> paymentsFiles = paymentFileRepository
                                            .findAllByDateOfCreation(operationInput.getCreationDate());

        if (paymentsFiles.isEmpty()){
            throw new NoSuchElementException("There are no payments files with created on this date.");
        }

        List<GetByPaymentFileByDateResponse> getByPaymentFileByDateResponses = new ArrayList<>();

        for (PaymentsFile file: paymentsFiles) {
            getByPaymentFileByDateResponses.add(
                    GetByPaymentFileByDateResponse.builder()
                            .paymentFileID(file.getUuid())
                            .invoicesForPayment(file.getInvoicesForPayment())
                            .dateOfCreation(file.getDateOfCreation())
                            .Employee(file.getEmployee())
                            .sent(file.isSent())
                            .build()
            );
        }

        return getByPaymentFileByDateResponses;
    }
}
