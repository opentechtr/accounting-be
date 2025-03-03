package com.otcp.accounting.currentAccount.controller;

import com.otcp.accounting.currentAccount.entity.CurrentAccount;
import com.otcp.accounting.currentAccount.service.CurrentAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/current-accounts")
public class CurrentAccountController {

    private final CurrentAccountService currentAccountService;

    public CurrentAccountController(CurrentAccountService currentAccountService) {
        this.currentAccountService = currentAccountService;
    }

    @GetMapping
    public List<CurrentAccount> getAllCurrentAccounts() {
        return currentAccountService.getAllCurrentAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrentAccount> getCurrentAccountById(@PathVariable Long id) {
        Optional<CurrentAccount> currentAccount = currentAccountService.getCurrentAccountById(id);
        return currentAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CurrentAccount createCurrentAccount(@RequestBody CurrentAccount currentAccount) {
        return currentAccountService.saveCurrentAccount(currentAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrentAccount> updateCurrentAccount(@PathVariable Long id, @RequestBody CurrentAccount currentAccount) {
        try {
            return ResponseEntity.ok(currentAccountService.updateCurrentAccount(id, currentAccount));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrentAccount(@PathVariable Long id) {
        currentAccountService.deleteCurrentAccount(id);
        return ResponseEntity.noContent().build();
    }
}