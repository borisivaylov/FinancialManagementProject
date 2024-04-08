package com.tinqin.financialManagementProject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paymentUnit")
public class PaymentUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "payment_file_uuid")
    private UUID paymentFileUuid;
    private String invoiceId;
    private String invoiceCreatorName;
    private String invoiceReceiverName;
    private String invoiceCreatorIBAN;
    private String invoiceReceiverIBAN;
    private double sum;
}
