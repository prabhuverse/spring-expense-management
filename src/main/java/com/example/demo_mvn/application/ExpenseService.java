package com.example.demo_mvn.application;

import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.application.mapper.EntityMappers;
import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.ExpenseCategory;
import com.example.demo_mvn.domain.model.repository.ExpenseRepository;
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
	EntityMappers entityMappers;

	@Autowired
	UserService userService;

	public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
		Expense expense = entityMappers.toExpenseEntity(expenseDTO);
		expense.setUser(userService.findUserById(expense.getUser().getId()));
		try {
			expense = repository.save(expense);
			log.info("Persisted expense object {}", expense);
		} catch (DataIntegrityViolationException e) {
			log.error("unable to persist expense object {} cause ", expense, e);
			return null;
		}
		return entityMappers.toExpenseDTO(expense);
	}

	public List<ExpenseDTO> listExpenseByType(ExpenseCategory category) {
		List<Expense> expenses = repository.findByCategory(category);
		List<ExpenseDTO> expenseDTOS = new ArrayList<>();
		if (!CollectionUtils.isEmpty(expenses)) {
			for (Expense expense : expenses) {
				expense.getUser().setExpenses(null);
				expenseDTOS.add(entityMappers.toExpenseDTO(expense));
			}
		}
		return expenseDTOS;
	}

	public ExpenseDTO deleteExpense(Long id) {
		Optional<Expense> expense = repository.findById(id);
		if (expense.isPresent()) {
			repository.deleteById(id);
			log.info("Expense delete {}", id);
			return entityMappers.toExpenseDTO(expense.get());
		}
		return null;
	}

	public ExpenseDTO getExpenseById(Long id) {
		Optional<Expense> expense = repository.findById(id);
		if (expense.isPresent()) {
			log.info("Expense found by {}", id);
			return entityMappers.toExpenseDTO(expense.get());
		}
		return null;
	}
}
