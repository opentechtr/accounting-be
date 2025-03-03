package com.otcp.accounting.currentAccount.service.impl;

import com.otcp.accounting.currentAccount.entity.CurrentAccount;
import com.otcp.accounting.currentAccount.service.CurrentAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CurrentAccountServiceImpl implements CurrentAccountService {
    @Override
    public List<CurrentAccount> getAllCurrentAccounts() {
        return null;
    }

    @Override
    public Optional<CurrentAccount> getCurrentAccountById(Long id) {
        return Optional.empty();
    }

    @Override
    public CurrentAccount saveCurrentAccount(CurrentAccount currentAccount) {
        return null;
    }

    @Override
    public CurrentAccount updateCurrentAccount(Long id, CurrentAccount currentAccount) {
        return null;
    }

    @Override
    public void deleteCurrentAccount(Long id) {

    }
}
