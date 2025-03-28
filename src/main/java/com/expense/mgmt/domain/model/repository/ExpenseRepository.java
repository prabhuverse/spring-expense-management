package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ExpenseRepository {

    Mono<Expense> save(ExpenseEntity expense);

    Mono<Expense> findById(Long id);

    Flux<Expense> findAll();

    Flux<Expense> findByCategory(ExpenseCategory category);

    void deleteById(Long id);

    void updateFileId(Expense expense);
}
