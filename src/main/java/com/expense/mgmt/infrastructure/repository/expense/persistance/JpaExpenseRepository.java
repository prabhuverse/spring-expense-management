package com.expense.mgmt.infrastructure.repository.expense.persistance;

import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

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
    public List<Expense> findByCategory(ExpenseCategory category) {
        List<ExpenseEntity> expenses = expenseRepository.findByCategory(category);
        return expenses.stream().map(EntityMappers::toExpense).toList();
    }

    @Override
    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

}
