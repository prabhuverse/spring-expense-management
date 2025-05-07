package com.expense.mgmt.infrastructure.repository.expense.persistance;

import com.expense.mgmt.domain.model.dto.expense.ExpenseCategoryResponse;
import com.expense.mgmt.infrastructure.repository.entity.EntityMappers;
import com.expense.mgmt.domain.model.dto.expense.Expense;
import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// implements the technical details
@RequiredArgsConstructor
@Repository
public class JpaExpenseRepository implements ExpenseRepository {

    private final SpringDataExpenseRepository expenseRepository;

    @Override
    public Expense save(ExpenseEntity expense) {
        return EntityMappers.toExpense(expenseRepository.save(expense));
    }

    public void updateFileId(Expense expense) {
        expenseRepository.updateExpenseFile(expense.getId(), expense.getExpenseFile().id());
    }

    @Override
    public Optional<Expense> findById(Long id) {
        Optional<ExpenseEntity> expenses = expenseRepository.findById(id);
        return Optional.of(EntityMappers.toExpense(expenses.get()));
    }

    @Override
    public List<Expense> findAll() {
        List<ExpenseEntity> expenses = expenseRepository.findAll();
        return expenses.stream().map(EntityMappers::toExpense).toList();
    }

    @Override
    public ExpenseCategoryResponse findByCategory(ExpenseCategory category, Pageable pageable) {
        Page<ExpenseEntity> expenses = expenseRepository.findByCategory(category, pageable);
        return ExpenseCategoryResponse.builder()
                .expenses(expenses.stream().map(EntityMappers::toExpense).toList())
                .hasNext(expenses.hasNext())
                .totalElements(expenses.getTotalElements())
                .totalPages(expenses.getTotalPages())
                .build();
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

}
