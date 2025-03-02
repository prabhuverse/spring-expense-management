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
		return ExpenseDTO.builder().id(expense.getId()).category(expense.getCategory()).amount(expense.getAmount())
				.createdOn(expense.getCreatedOn()).description(expense.getDescription()).build();
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
		return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getName(), new ArrayList<>(),
				user.getCreatedOn(), user.getUpdateOn(), user.getVersion());
	}
}
