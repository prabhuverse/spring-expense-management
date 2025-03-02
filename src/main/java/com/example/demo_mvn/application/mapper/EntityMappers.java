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

	public ExpenseDTO toDTO(Expense expense) {
		return toDTO(expense, true);
	}

	public ExpenseDTO toDTO(Expense expense, boolean requireUser) {
		if (requireUser) {
			return new ExpenseDTO(expense.getId(), expense.getCategory(), expense.getDescription(), expense.getAmount(),
					expense.getCreatedOn(), toUserDTO(expense.getUser()));
		} else {
			return ExpenseDTO.builder().id(expense.getId()).category(expense.getCategory()).amount(expense.getAmount())
					.createdOn(expense.getCreatedOn()).description(expense.getDescription()).build();
		}
	}

	public Expense toEntity(ExpenseDTO dto) {
		return Expense.builder().id(dto.getId()).category(dto.getCategory()).createdOn(dto.getCreatedOn())
				.description(dto.getDescription()).amount(dto.getAmount()).user(toUser(dto.getUser())).build();
	}

	public User toUser(UserDTO userDTO) {
		User user = User.builder().id(userDTO.getId()).email(userDTO.getEmail()).password(userDTO.getPassword())
				.createdOn(userDTO.getCredatedOn()).name(userDTO.getName()).build();

		if (!CollectionUtils.isEmpty(userDTO.getExpenses())) {
			List<Expense> expenseList = new ArrayList<>();
			userDTO.getExpenses().forEach(expenseDto -> expenseList.add(toEntity(expenseDto)));
			user.setExpenses(expenseList);
		}
		return user;
	}

	public UserDTO toUserDTO(User user) {
		List<ExpenseDTO> expenseList = new ArrayList<>();;
		if (!CollectionUtils.isEmpty(user.getExpenses())) {
			user.getExpenses().forEach(expenseDto -> expenseList.add(toDTO(expenseDto, false)));
		}
		return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getName(), expenseList,
				user.getCreatedOn(), user.getUpdateOn(), user.getVersion());
	}
}
