package com.tinqin.financialManagementProject.persistence.entity;

import com.tinqin.accountingproject.persistence.entity.Invoice;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paymentsFile")
public class PaymentsFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="payment_invoices", joinColumns=@JoinColumn(name="payment_uuid"))
    @Column(name = "invoice_id")
    private List<String> invoicesForPayment;
    @OneToMany(mappedBy = "paymentFileUuid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentUnit> paymentUnitList;
    private Timestamp dateOfCreation;
    private String Employee;
    private boolean sent;
}
