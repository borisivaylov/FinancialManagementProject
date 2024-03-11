package com.tinqin.financialManagementProject.core.paymentsFile.getAllSortedList;

import com.tinqin.financialManagementProject.api.paymentsFile.getAllSortedList.GetAllPaymentFilesListOperation;
import com.tinqin.financialManagementProject.api.paymentsFile.getAllSortedList.GetAllPaymentFilesListRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.getAllSortedList.GetAllPaymentFilesListResponse;
import com.tinqin.financialManagementProject.persistence.entity.PaymentsFile;
import com.tinqin.financialManagementProject.persistence.repository.PaymentFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllSortedListOperationProcessor implements GetAllPaymentFilesListOperation {

    private final PaymentFileRepository paymentFileRepository;
    @Override
    public List<GetAllPaymentFilesListResponse> process(GetAllPaymentFilesListRequest operationInput) {

        List<PaymentsFile> paymentsFiles = paymentFileRepository.findAllByOrderByDateOfCreation();
        List<GetAllPaymentFilesListResponse> getAllPaymentFilesListResponseList = new ArrayList<>();

        for (PaymentsFile paymentsfile: paymentsFiles) {
            getAllPaymentFilesListResponseList.add(GetAllPaymentFilesListResponse.builder()
                            .paymentFileID(paymentsfile.getUuid())
                            .invoicesForPayment(paymentsfile.getInvoicesForPayment())
                            .dateOfCreation(paymentsfile.getDateOfCreation())
                            .Employee(paymentsfile.getEmployee())
                            .sent(paymentsfile.isSent())
                            .build());
        }

        return getAllPaymentFilesListResponseList;
    }
}
