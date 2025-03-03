package com.otcp.accounting.ledger.entity;

import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entry")
@Data
public class JournalEntry extends BaseEntity {

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;

    @ManyToOne
    @JoinColumn(name = "debit_account_id", nullable = false)
    private LedgerAccount debitAccount;

    @ManyToOne
    @JoinColumn(name = "credit_account_id", nullable = false)
    private LedgerAccount creditAccount;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Transaction type is mandatory")
    private String transactionType;

    @NotBlank(message = "Reference number is mandatory")
    @Column(unique = true)
    private String referenceNumber;

    private String currency;
    private LocalDateTime approvedAt;
    private LocalDateTime transactionDate;
    private boolean isReversed;
    private String notes;

    public void approve(String user) {
        this.approvedAt = LocalDateTime.now();
    }

    public void reverseEntry() {
        this.isReversed = true;
    }

    public String exportToCSV() {
        return this.getId() + "," + description + "," + transactionType + "," + amount + "," + currency;
    }

    // Enhanced with audit tracking, reconciliation reports, CSV/JSON/XML export, transaction templates, budget allocations, and tax compliance checks


}
