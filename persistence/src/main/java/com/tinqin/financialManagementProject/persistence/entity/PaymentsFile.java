package com.tinqin.financialManagementProject.persistence.entity;

import com.tinqin.accountingproject.persistence.entity.Invoice;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name="payment_invoices", joinColumns=@JoinColumn(name="payment_uuid"))
    @Column(name = "invoice_id") // Assuming "invoice_id" is the name of the column to store invoice IDs
    private List<String> invoicesForPayment;
    private Timestamp dateOfCreation;
    private String Employee;
    private boolean sent;
}
