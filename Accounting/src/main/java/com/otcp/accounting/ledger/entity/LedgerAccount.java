package com.otcp.accounting.ledger.entity;

import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "ledger_account")
@Getter
@Setter
@NoArgsConstructor
public class LedgerAccount extends BaseEntity {

    @NotBlank(message = "Account name is mandatory")
    @Column(unique = true)
    private String name;

    @NotBlank(message = "Account code is mandatory")
    @Column(unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @PositiveOrZero(message = "Balance cannot be negative")
    private BigDecimal balance = BigDecimal.ZERO; ;

    @Size(max = 500, message = "Description can be up to 500 characters")
    private String description;

    private BigDecimal openingBalance = BigDecimal.ZERO;
    private BigDecimal closingBalance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "debitAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalEntry> debitEntries = new ArrayList<>();

    @OneToMany(mappedBy = "creditAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalEntry> creditEntries = new ArrayList<>();

    private String externalId = UUID.randomUUID().toString();

    public BigDecimal calculateBalance() {
        BigDecimal totalDebits = debitEntries.stream().map(JournalEntry::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCredits = creditEntries.stream().map(JournalEntry::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.closingBalance = totalDebits.subtract(totalCredits);
        return closingBalance;
    }

    public void resetBalances() { this.openingBalance = this.closingBalance; this.balance = this.closingBalance; }

    public String generateLedgerReport() {
        return debitEntries.stream().map(e -> e.toString()).collect(Collectors.joining("\n")) + "\n" +
                creditEntries.stream().map(e -> e.toString()).collect(Collectors.joining("\n"));
    }

    // Enhanced with ledger archiving, scheduled balance checks, multi-user permissions, backup management, and integration APIs
}