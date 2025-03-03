
package com.otcp.accounting.ledger.service;

import com.otcp.accounting.ledger.entity.JournalEntry;
import com.otcp.accounting.ledger.entity.LedgerAccount;

import java.util.List;
public interface LedgerService {
    LedgerAccount createAccount(LedgerAccount account);
    LedgerAccount getAccountById(Long id);
    List<LedgerAccount> getAllAccounts();
    LedgerAccount updateAccount(Long id, LedgerAccount account);
    void deleteAccount(Long id);
    void archiveLedger(Long id);

    JournalEntry createJournalEntry(JournalEntry entry);
    JournalEntry getJournalEntryById(Long id);
    List<JournalEntry> getAllJournalEntries();
    JournalEntry updateJournalEntry(Long id, JournalEntry entry);
    void deleteJournalEntry(Long id);
}
