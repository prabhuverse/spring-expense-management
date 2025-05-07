package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.expense.Expense;
import com.expense.mgmt.domain.model.dto.expense.ExpenseCategoryResponse;
import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense save(ExpenseEntity expense);

    Optional<Expense> findById(Long id);

    List<Expense> findAll();

    ExpenseCategoryResponse findByCategory(ExpenseCategory category, Pageable pageable);

    void deleteById(Long id);

    void updateFileId(Expense expense);
}
