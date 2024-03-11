package com.tinqin.financialManagementProject.rest;

import com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment.AddInvoiceForPaymentRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment.AddInvoiceForPaymentResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.create.CreatePaymentsFileResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.delete.DeletePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.delete.DeletePaymentsFileResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.getAllSortedList.GetAllPaymentFilesListRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.getAllSortedList.GetAllPaymentFilesListResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.getById.GetPaymentsFileByIdRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.getById.GetPaymentsFileByIdResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.getByPaymentDate.GetByPaymentFileByDateRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.getByPaymentDate.GetByPaymentFileByDateResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.removeInvoiceForPayment.RemoveInvoiceForPaymentRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.removeInvoiceForPayment.RemoveInvoiceForPaymentResponse;
import com.tinqin.financialManagementProject.api.paymentsFile.update.UpdatePaymentsFileRequest;
import com.tinqin.financialManagementProject.api.paymentsFile.update.UpdatePaymentsFileResponse;
import com.tinqin.financialManagementProject.core.paymentsFile.addInvoiceForPayment.AddInvoiceForPaymentOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.create.CreatePaymentsFileOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.delete.DeletePaymentsFileOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.getAllSortedList.GetAllSortedListOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.getByPaymentDate.GetPaymentsFileByDateOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.getByid.GetPaymentsFileByIdOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.removeInvoiceforPayment.RemoveInvoiceForPaymentOperationProcessor;
import com.tinqin.financialManagementProject.core.paymentsFile.update.UpdatePaymentsFileOperationProcessor;
import feign.Param;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paymentsFile")
@RequiredArgsConstructor
public class PaymentFileController {

    private final CreatePaymentsFileOperationProcessor createPaymentsFileOperationProcessor;
    private final GetPaymentsFileByIdOperationProcessor getPaymentsFileByIdOperationProcessor;
    private final DeletePaymentsFileOperationProcessor deletePaymentsFileOperationProcessor;
    private final GetPaymentsFileByDateOperationProcessor getPaymentsFileByDateOperationProcessor;
    private final AddInvoiceForPaymentOperationProcessor addInvoiceForPaymentOperationProcessor;
    private final RemoveInvoiceForPaymentOperationProcessor removeInvoiceForPaymentOperationProcessor;
    private final GetAllSortedListOperationProcessor getAllSortedListOperationProcessor;
    private final UpdatePaymentsFileOperationProcessor updatePaymentsFileOperationProcessor;

    @PostMapping ("/createFile")
    CreatePaymentsFileResponse newPaymentsFile(@RequestBody CreatePaymentsFileRequest createPaymentsFileRequest){
        return createPaymentsFileOperationProcessor.process(createPaymentsFileRequest);
    }
    @GetMapping ("/getById/{uuid}")
    GetPaymentsFileByIdResponse getPaymentsFileById(@PathVariable UUID uuid){
        return getPaymentsFileByIdOperationProcessor.process(GetPaymentsFileByIdRequest.builder()
                                                                    .paymentFileID(uuid).build());
    }
    @PostMapping ("/deleteFile/{uuid}")
    DeletePaymentsFileResponse deletePaymentsFileById(@PathVariable UUID uuid){
        return deletePaymentsFileOperationProcessor.process(DeletePaymentsFileRequest.builder()
                                                                .paymentFileId(uuid).build());
    }
    @GetMapping ("/getByPaymentDate")
    List<GetByPaymentFileByDateResponse> getByPaymentFileByDate(@RequestBody GetByPaymentFileByDateRequest getByPaymentFileByDateRequest){
        return getPaymentsFileByDateOperationProcessor.process(getByPaymentFileByDateRequest);
    }
    @PostMapping("/addInvoice")
    AddInvoiceForPaymentResponse addInvoiceForPayment(@RequestBody AddInvoiceForPaymentRequest addInvoiceForPaymentRequest){
        return addInvoiceForPaymentOperationProcessor.process(addInvoiceForPaymentRequest);
    }
    @PostMapping("/removeInvoice")
    RemoveInvoiceForPaymentResponse removeInvoiceForPayment(@RequestBody RemoveInvoiceForPaymentRequest removeInvoiceForPaymentRequest){
    return removeInvoiceForPaymentOperationProcessor.process(removeInvoiceForPaymentRequest);
    }
    @GetMapping("/getAllSortedByDate")
    List<GetAllPaymentFilesListResponse> getAllSortedByDate(@RequestBody GetAllPaymentFilesListRequest getAllPaymentFilesListRequest){
        return getAllSortedListOperationProcessor.process(getAllPaymentFilesListRequest);
    }
    @PostMapping ("/update")
    UpdatePaymentsFileResponse updatePaymentsFile(@RequestBody UpdatePaymentsFileRequest updatePaymentsFileRequest){
        return updatePaymentsFileOperationProcessor.process(updatePaymentsFileRequest);
    }



}
