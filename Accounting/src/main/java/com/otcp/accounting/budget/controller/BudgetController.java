
package com.otcp.accounting.budget.controller;

import com.otcp.accounting.budget.entity.Budget;
import com.otcp.accounting.budget.entity.Expense;
import com.otcp.accounting.budget.entity.Income;
import com.otcp.accounting.budget.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.createBudget(budget));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.updateBudget(id, budget));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetService.getBudgetById(id));
    }

    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @PostMapping("/{id}/expenses")
    public ResponseEntity<Void> addExpense(@PathVariable Long id, @RequestBody Expense expense) {
        budgetService.addExpense(id, expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{id}/incomes")
    public ResponseEntity<Void> addIncome(@PathVariable Long id, @RequestBody Income income) {
        budgetService.addIncome(id, income);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable Long id) {
        return ResponseEntity.ok(budgetService.getExpensesByBudget(id));
    }

    @GetMapping("/{id}/incomes")
    public ResponseEntity<List<Income>> getIncomes(@PathVariable Long id) {
        return ResponseEntity.ok(budgetService.getIncomesByBudget(id));
    }

    @PatchMapping("/{id}/adjust")
    public ResponseEntity<Budget> adjustBudget(@PathVariable Long id, @RequestParam BigDecimal newTotal) {
        return ResponseEntity.ok(budgetService.adjustBudgetAmount(id, newTotal));
    }
}
