package com.otcp.accounting.currentAccount.service;
import com.otcp.accounting.currentAccount.entity.CurrentAccount;

import java.util.List;
import java.util.Optional;

public interface CurrentAccountService {
    List<CurrentAccount> getAllCurrentAccounts();

    Optional<CurrentAccount> getCurrentAccountById(Long id);

    CurrentAccount saveCurrentAccount(CurrentAccount currentAccount);

    CurrentAccount updateCurrentAccount(Long id, CurrentAccount currentAccount);
    void deleteCurrentAccount(Long id);

}
