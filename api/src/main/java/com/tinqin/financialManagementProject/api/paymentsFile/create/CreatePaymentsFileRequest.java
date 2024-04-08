package com.tinqin.financialManagementProject.api.paymentsFile.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentsFileRequest implements OperationInput {

    @JsonProperty("DateOfPayment")
    private Timestamp dateOfPayment;
    @JsonProperty("IBANPayment")
    private String IBANPayment;

}
