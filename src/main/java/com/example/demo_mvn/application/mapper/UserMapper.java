package com.example.demo_mvn.application.mapper;


import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.domain.model.Expense;
import com.example.demo_mvn.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

	@Autowired
	public ExpenseMapper expenseMapper;

	public User toUser(UserDTO userDTO) {
		User user = User.builder().id(userDTO.id()).email(userDTO.email()).password(userDTO.password())
				.createdOn(userDTO.credatedOn()).name(userDTO.name()).build();

		if (!CollectionUtils.isEmpty(userDTO.expenses())) {
			List<Expense> expenseList = new ArrayList<>();
			userDTO.expenses().forEach(expenseDto -> expenseList.add(expenseMapper.toEntity(expenseDto)));
			user.setExpenses(expenseList);
		}
		return user;
	}

	public UserDTO toUserDTO(User user) {
		List<ExpenseDTO> expenseList = new ArrayList<>();;
		if (!CollectionUtils.isEmpty(user.getExpenses())) {
			user.getExpenses().forEach(expenseDto -> expenseList.add(expenseMapper.toDTO(expenseDto)));
		}
		return new UserDTO(user.getId(), user.getEmail(), user.getPassword(), user.getName(), expenseList,
				user.getCreatedOn());
	}
}
