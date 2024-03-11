package com.tinqin.financialManagementProject.api.paymentsFile.getById;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentsFileByIdRequest implements OperationInput {
    @JsonProperty("PaymentFileId")
    private UUID paymentFileID;
}
