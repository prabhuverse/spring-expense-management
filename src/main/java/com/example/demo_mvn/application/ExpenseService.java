package com.example.demo_mvn.application;


import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.application.mapper.ExpenseMapper;
import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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


}
