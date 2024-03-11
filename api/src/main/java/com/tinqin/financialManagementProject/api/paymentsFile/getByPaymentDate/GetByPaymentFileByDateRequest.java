package com.tinqin.financialManagementProject.api.paymentsFile.getByPaymentDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetByPaymentFileByDateRequest implements OperationInput {

    @JsonProperty("creationDate")
    private Timestamp creationDate;
}
