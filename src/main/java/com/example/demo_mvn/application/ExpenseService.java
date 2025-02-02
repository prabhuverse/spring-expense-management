package com.example.demo_mvn.application;


import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.application.mapper.ExpenseMapper;
import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.ExpenseCategory;
import com.example.demo_mvn.domain.model.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ExpenseService {

	@Autowired
	ExpenseRepository repository;

	@Autowired
	ExpenseMapper expenseMapper;

	public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
		Expense expense = expenseMapper.toEntity(expenseDTO);
		expense = repository.save(expense);
		log.info("Persisted expense object {}", expense);
		return expenseMapper.toDTO(expense);
	}


	public List<ExpenseDTO> listExpenseByType(ExpenseCategory category) {
		List<Expense> expenses = repository.findByCategory(category);
		List<ExpenseDTO> expenseDTOS = new ArrayList<>();
		if (!CollectionUtils.isEmpty(expenses)) {
			for (Expense expense : expenses) {
				expenseDTOS.add(expenseMapper.toDTO(expense));
			}
		}
		return expenseDTOS;
	}

}
