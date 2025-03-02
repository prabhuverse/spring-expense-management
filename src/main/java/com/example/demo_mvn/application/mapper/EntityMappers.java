package com.example.demo_mvn.application.mapper;


import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityMappers {

	public ExpenseDTO toExpenseDTO(Expense expense) {
		return toExpenseDTO(expense, true);
	}

	public ExpenseDTO toExpenseDTO(Expense expense, boolean requireUser) {
		if (requireUser) {
			return new ExpenseDTO(expense.getId(), expense.getCategory(), expense.getDescription(), expense.getAmount(),
					expense.getCreatedOn(), toUserDTO(expense.getUser()));
		} else {
			return ExpenseDTO.builder().id(expense.getId()).category(expense.getCategory()).amount(expense.getAmount())
					.createdOn(expense.getCreatedOn()).description(expense.getDescription()).build();
		}
	}

	public Expense toExpenseEntity(ExpenseDTO dto) {
		return Expense.builder().id(dto.getId()).category(dto.getCategory()).createdOn(dto.getCreatedOn())
				.description(dto.getDescription()).amount(dto.getAmount()).user(toUserEntity(dto.getUser())).build();
	}

	public User toUserEntity(UserDTO userDTO) {
		User user = User.builder().id(userDTO.getId()).email(userDTO.getEmail()).password(userDTO.getPassword())
				.createdOn(userDTO.getCredatedOn()).name(userDTO.getName()).build();

		if (!CollectionUtils.isEmpty(userDTO.getExpenses())) {
			List<Expense> expenseList = new ArrayList<>();
			userDTO.getExpenses().forEach(expenseDto -> expenseList.add(toExpenseEntity(expenseDto)));
			user.setExpenses(expenseList);
		}
		return user;
	}

	public UserDTO toUserDTO(User user) {
		List<ExpenseDTO> expenseList = new ArrayList<>();;
		if (!CollectionUtils.isEmpty(user.getExpenses())) {
			user.getExpenses().forEach(expenseDto -> expenseList.add(toExpenseDTO(expenseDto, false)));
		}
		return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getName(), expenseList,
				user.getCreatedOn(), user.getUpdateOn(), user.getVersion());
	}
}
