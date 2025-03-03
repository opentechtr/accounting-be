
package com.otcp.accounting.ledger.service.impl;

import com.otcp.accounting.ledger.entity.JournalEntry;
import com.otcp.accounting.ledger.entity.LedgerAccount;
import com.otcp.accounting.ledger.service.LedgerService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LedgerServiceImpl implements LedgerService {


    @Override
    public LedgerAccount createAccount(LedgerAccount account) {
        return null;
    }

    @Override
    public LedgerAccount getAccountById(Long id) {
        return null;
    }

    @Override
    public List<LedgerAccount> getAllAccounts() {
        return null;
    }

    @Override
    public LedgerAccount updateAccount(Long id, LedgerAccount account) {
        return null;
    }

    @Override
    public void deleteAccount(Long id) {

    }

    @Override
    public void archiveLedger(Long id) {

    }

    @Override
    public JournalEntry createJournalEntry(JournalEntry entry) {
        return null;
    }

    @Override
    public JournalEntry getJournalEntryById(Long id) {
        return null;
    }

    @Override
    public List<JournalEntry> getAllJournalEntries() {
        return null;
    }

    @Override
    public JournalEntry updateJournalEntry(Long id, JournalEntry entry) {
        return null;
    }

    @Override
    public void deleteJournalEntry(Long id) {

    }
}
