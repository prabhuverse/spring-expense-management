package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.Expense;
import com.expense.mgmt.domain.model.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(Long id);

    List<Expense> findAll();

    List<Expense> findByCategory(ExpenseCategory category);

    void deleteById(Long id);
}
