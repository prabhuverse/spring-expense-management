package com.expense.mgmt.application;

import com.expense.mgmt.application.dto.ExpenseDTO;
import com.expense.mgmt.application.mapper.EntityMappers;
import com.expense.mgmt.domain.model.Expense;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    @Autowired
    UserService userService;

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = EntityMappers.toExpenseEntity(expenseDTO);
        expense.setUser(userService.findUserById(expense.getUser().getId()));
        expense = repository.save(expense);
        log.info("Persisted expense object {}", expense);
        return EntityMappers.toExpenseDTO(expense);
    }

    public List<ExpenseDTO> listExpenseByType(ExpenseCategory category) {
        List<Expense> expenses = repository.findByCategory(category);
        List<ExpenseDTO> expenseDTOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(expenses)) {
            for (Expense expense : expenses) {
                //expense.getUser().setExpenses(null);
                expenseDTOS.add(EntityMappers.toExpenseDTO(expense));
            }
        }
        return expenseDTOS;
    }

    public ExpenseDTO deleteExpense(Long id) {
        Optional<Expense> expense = repository.findById(id);
        if (expense.isPresent()) {
            repository.deleteById(id);
            log.info("Expense delete {}", id);
            return EntityMappers.toExpenseDTO(expense.get());
        }
        return null;
    }

    public ExpenseDTO getExpenseById(Long id) {
        Optional<Expense> expense = repository.findById(id);
        if (expense.isPresent()) {
            log.info("Expense found by {}", id);
            return EntityMappers.toExpenseDTO(expense.get());
        }
        return null;
    }
}
