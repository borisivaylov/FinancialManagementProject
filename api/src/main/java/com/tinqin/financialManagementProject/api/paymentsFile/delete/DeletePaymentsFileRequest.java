package com.tinqin.financialManagementProject.api.paymentsFile.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletePaymentsFileRequest implements OperationInput {

    @JsonProperty("PaymentFileId")
    private UUID paymentFileId;
}
