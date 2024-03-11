package com.tinqin.financialManagementProject.api.paymentsFile.addInvoiceForPayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddInvoiceForPaymentRequest implements OperationInput {

    @JsonProperty("invoiceId")
    private String invoiceId;
    @JsonProperty ("paymentFileId")
    private UUID paymentFileId;
}
