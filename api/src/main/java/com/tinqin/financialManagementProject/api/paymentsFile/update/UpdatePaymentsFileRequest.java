package com.tinqin.financialManagementProject.api.paymentsFile.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePaymentsFileRequest implements OperationInput {
    @JsonProperty("PaymentFileId")
    private UUID paymentFileID;
    @JsonProperty("DateOfCreation")
    private Timestamp dateOfCreation;
    @JsonProperty("EmployeeSign")
    private String Employee;
}
