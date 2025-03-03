
package com.otcp.accounting.budget.service;

import com.otcp.accounting.budget.entity.Budget;
import com.otcp.accounting.budget.entity.Expense;
import com.otcp.accounting.budget.entity.Income;

import java.math.BigDecimal;
import java.util.List;

public interface BudgetService {
    Budget createBudget(Budget budget);
    Budget updateBudget(Long id, Budget budget);
    void deleteBudget(Long id);
    Budget getBudgetById(Long id);
    List<Budget> getAllBudgets();
    BigDecimal calculateRemainingAmount(Long budgetId);
    void addExpense(Long budgetId, Expense expense);
    void addIncome(Long budgetId, Income income);
    List<Expense> getExpensesByBudget(Long budgetId);
    List<Income> getIncomesByBudget(Long budgetId);
    Budget adjustBudgetAmount(Long budgetId, BigDecimal newTotal);
}
