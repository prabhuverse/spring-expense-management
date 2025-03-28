package com.expense.mgmt.infrastructure.repository.expense.persistance;

import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// implements the technical details
@RequiredArgsConstructor
@Repository
public class JpaExpenseRepository implements ExpenseRepository {

    private final SpringDataExpenseRepository expenseRepository;

    @Override
    public Mono<Expense> save(ExpenseEntity expense) {
        return expenseRepository.save(expense).map(EntityMappers::toExpense);
    }

    public void updateFileId(Expense expense) {
        //expenseRepository.updateExpenseFile(expense.getId(), expense.getExpenseFile().id());
    }

    @Override
    public Mono<Expense> findById(Long id) {
        return expenseRepository.findById(id).map(EntityMappers::toExpense);
    }

    @Override
    public Flux<Expense> findAll() {
        return expenseRepository.findAll().map(EntityMappers::toExpense);
    }

    @Override
    public Flux<Expense> findByCategory(ExpenseCategory category) {
        return expenseRepository.findByCategory(category).map(EntityMappers::toExpense);
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

}
