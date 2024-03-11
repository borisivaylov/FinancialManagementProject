package com.tinqin.financialManagementProject.core.paymentsFile.getByid;


import com.tinqin.financialManagementProject.api.paymentsFile.getById.GetPaymentsFileByIdOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.getById.GetPaymentsFileByIdRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.getById.GetPaymentsFileByIdResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GetPaymentsFileByIdOperationProcessor implements GetPaymentsFileByIdOperation {

    private final PaymentFileRepository paymentFileRepository;

    @Override
    public GetPaymentsFileByIdResponse process(GetPaymentsFileByIdRequest operationInput) {

        PaymentsFile paymentsFile = paymentFileRepository.findById(operationInput.getPaymentFileID())
                                    .orElseThrow(()-> new NoSuchElementException("There is no such payments file."));



        return GetPaymentsFileByIdResponse.builder()
                .paymentFileID(paymentsFile.getUuid())
                .invoicesForPayment(paymentsFile.getInvoicesForPayment())
                .dateOfCreation(paymentsFile.getDateOfCreation())
                .Employee(paymentsFile.getEmployee())
                .sent(paymentsFile.isSent())
                .build();
    }





}
