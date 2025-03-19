package com.otcp.accounting.invoice.entity;

import com.otcp.accounting.common.base.BaseEntity;
import com.otcp.accounting.currentAccount.entity.CurrentAccount;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Invoice extends BaseEntity {
    @NotBlank(message = "Invoice number is required")
    private String invoiceNumber;

    @PastOrPresent
    private LocalDate issueDate;

    @FutureOrPresent
    private LocalDate dueDate;

    @Positive
    private BigDecimal totalAmount;

    @PositiveOrZero
    private BigDecimal taxAmount;

    @Positive
    private BigDecimal netAmount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @Lob
    private byte[] invoiceFile;

    private String fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CurrentAccount currentAccount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> items = new ArrayList<>();

    // Getters, setters, constructors







}
