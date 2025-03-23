package com.expense.mgmt.application;

import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    @Autowired
    UserService userService;

    public Expense createExpense(Expense expense) {
        ExpenseEntity expenseEntity = EntityMappers.toExpenseEntity(expense);
        expenseEntity.setUser(EntityMappers.toUserEntity(userService.findUserById(expense.getUser().getId())));
        expense = repository.save(expenseEntity);
        log.info("Persisted expense object {}", expense);
        return expense;
    }

    public List<Expense> listExpenseByType(ExpenseCategory category) {
        return repository.findByCategory(category);
    }

    public Expense deleteExpense(Long id) {
        return repository.findById(id).get();
    }

    public Expense getExpenseById(Long id) {
        return repository.findById(id).get();
    }
}
