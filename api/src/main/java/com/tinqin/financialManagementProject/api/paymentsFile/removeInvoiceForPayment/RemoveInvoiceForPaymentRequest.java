package com.tinqin.financialManagementProject.api.paymentsFile.removeInvoiceForPayment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tinqin.financialManagementProject.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveInvoiceForPaymentRequest implements OperationInput {
    @JsonProperty("invoiceId")
    private String invoiceId;
    @JsonProperty ("paymentFileId")
    private UUID paymentFileId;
}
