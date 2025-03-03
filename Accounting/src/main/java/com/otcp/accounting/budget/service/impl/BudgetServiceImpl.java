
package com.otcp.accounting.budget.service.impl;

import com.otcp.accounting.budget.entity.Budget;
import com.otcp.accounting.budget.entity.Expense;
import com.otcp.accounting.budget.entity.Income;
import com.otcp.accounting.budget.service.BudgetService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Override
    public Budget createBudget(Budget budget) {
        return null;
    }

    @Override
    public Budget updateBudget(Long id, Budget budget) {
        return null;
    }

    @Override
    public void deleteBudget(Long id) {

    }

    @Override
    public Budget getBudgetById(Long id) {
        return null;
    }

    @Override
    public List<Budget> getAllBudgets() {
        return null;
    }

    @Override
    public BigDecimal calculateRemainingAmount(Long budgetId) {
        return null;
    }

    @Override
    public void addExpense(Long budgetId, Expense expense) {

    }

    @Override
    public void addIncome(Long budgetId, Income income) {

    }

    @Override
    public List<Expense> getExpensesByBudget(Long budgetId) {
        return null;
    }

    @Override
    public List<Income> getIncomesByBudget(Long budgetId) {
        return null;
    }

    @Override
    public Budget adjustBudgetAmount(Long budgetId, BigDecimal newTotal) {
        return null;
    }
}
