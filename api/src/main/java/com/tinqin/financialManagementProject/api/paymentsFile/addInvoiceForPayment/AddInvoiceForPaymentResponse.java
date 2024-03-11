package com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.accountingproject.persistence.entity.Invoice;
import com.tinqin.financialManagementProject.api.base.OperationResult;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddInvoiceForPaymentResponse implements OperationResult {
    @JsonProperty("PaymentFileId")
    private UUID paymentFileID;
    @JsonProperty("Invoices")
    private List<String> invoicesForPayment;
    @JsonProperty("DateOfCreation")
    private Timestamp dateOfCreation;
    @JsonProperty("EmployeeSign")
    private String Employee;
    @JsonProperty("Sent")
    private boolean sent;
    @JsonProperty("status")
    private String status;
}
