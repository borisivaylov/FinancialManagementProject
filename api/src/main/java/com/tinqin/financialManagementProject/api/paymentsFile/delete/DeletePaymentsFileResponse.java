package com.tinqin.financialManagementProject.api.paymentsFile.delete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeletePaymentsFileResponse implements OperationResult {

    @JsonProperty("status")
    private String status;
}
