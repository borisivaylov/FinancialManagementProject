package com.tinqin.financialManagementProject.api.paymentsFile.getById;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationResult;
import com.tinqin.financialManagementProject.persistence.entity.PaymentUnit;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentsFileByIdResponse implements OperationResult {

    @JsonProperty("PaymentFileId")
    private UUID paymentFileID;
    @JsonProperty("Invoices")
    private List<String> invoicesForPayment;
    @JsonProperty
    private List<PaymentUnit> paymentUnitList;
    @JsonProperty("DateOfCreation")
    private Timestamp dateOfCreation;
    @JsonProperty("EmployeeSign")
    private String Employee;
    @JsonProperty("Sent")
    private boolean sent;
}
