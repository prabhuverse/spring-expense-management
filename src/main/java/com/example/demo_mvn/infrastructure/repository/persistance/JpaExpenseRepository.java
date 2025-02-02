package com.example.demo_mvn.infrastructure.repository.persistance;

import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class JpaExpenseRepository implements ExpenseRepository {

    @Autowired
    SpringDataExpenseRepository expenseRepository;

    @Override
    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> findByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

}
