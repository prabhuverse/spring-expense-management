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
		return Expense.builder().id(dto.id()).category(dto.category()).createdOn(dto.createdOn())
				.description(dto.description()).amount(dto.amount()).user(dto.user()).build();
	}
}
