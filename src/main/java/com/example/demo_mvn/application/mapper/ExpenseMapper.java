package com.example.demo_mvn.application.mapper;


import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.domain.model.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

	public ExpenseDTO toDTO(Expense expense) {
		return new ExpenseDTO(expense.getId(), expense.getCategory(), expense.getDescription(), expense.getAmount(),
				expense.getCreatedOn(), expense.getUser());
	}

	public Expense toEntity(ExpenseDTO dto) {
		return new Expense(dto.id(), dto.category(), dto.createdOn(), dto.description(), dto.amount(), dto.user());
	}
}
