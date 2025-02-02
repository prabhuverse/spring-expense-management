package com.example.demo_mvn.domain.model.repository;

import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.ExpenseCategory;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

	Expense save(Expense expense);

	Optional<Expense> findById(Long id);

	List<Expense> findAll();

	List<Expense> findByCategory(ExpenseCategory category);
}
