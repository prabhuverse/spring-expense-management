package com.expense.mgmt.application.mapper;

import lombok.experimental.UtilityClass;

import com.expense.mgmt.application.dto.ExpenseDTO;
import com.expense.mgmt.application.dto.UserDTO;
import com.expense.mgmt.domain.model.Expense;
import com.expense.mgmt.domain.model.User;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EntityMappers {

    public ExpenseDTO toExpenseDTO(Expense expense) {
        return ExpenseDTO.builder().id(expense.getId()).category(expense.getCategory()).amount(expense.getAmount())
                .createdOn(expense.getCreatedOn()).description(expense.getDescription())
                .user(toUserDTO(expense.getUser())).build();
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
