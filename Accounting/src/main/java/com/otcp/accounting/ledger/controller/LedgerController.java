
package com.otcp.accounting.ledger.controller;
import com.otcp.accounting.ledger.entity.JournalEntry;
import com.otcp.accounting.ledger.entity.LedgerAccount;
import com.otcp.accounting.ledger.service.LedgerService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ledger")
public class LedgerController {
    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @PostMapping("/account")
    public ResponseEntity<LedgerAccount> createAccount(@Valid @RequestBody LedgerAccount account) {
        return new ResponseEntity<>(ledgerService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<LedgerAccount> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(ledgerService.getAccountById(id));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<LedgerAccount>> getAllAccounts() {
        return ResponseEntity.ok(ledgerService.getAllAccounts());
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<LedgerAccount> updateAccount(@PathVariable Long id, @Valid @RequestBody LedgerAccount account) {
        return ResponseEntity.ok(ledgerService.updateAccount(id, account));
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        ledgerService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/account/{id}/archive")
    public ResponseEntity<Void> archiveLedger(@PathVariable Long id) {
        ledgerService.archiveLedger(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/journal")
    public ResponseEntity<JournalEntry> createJournal(@Valid @RequestBody JournalEntry entry) {
        return new ResponseEntity<>(ledgerService.createJournalEntry(entry), HttpStatus.CREATED);
    }

    @GetMapping("/journal/{id}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable Long id) {
        return ResponseEntity.ok(ledgerService.getJournalEntryById(id));
    }

    @GetMapping("/journals")
    public ResponseEntity<List<JournalEntry>> getAllJournals() {
        return ResponseEntity.ok(ledgerService.getAllJournalEntries());
    }

    @PutMapping("/journal/{id}")
    public ResponseEntity<JournalEntry> updateJournal(@PathVariable Long id, @Valid @RequestBody JournalEntry entry) {
        return ResponseEntity.ok(ledgerService.updateJournalEntry(id, entry));
    }

    @DeleteMapping("/journal/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable Long id) {
        ledgerService.deleteJournalEntry(id);
        return ResponseEntity.noContent().build();
    }
}