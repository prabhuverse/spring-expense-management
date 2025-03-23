package com.expense.mgmt.infrastructure.repository.persistance;

import lombok.experimental.UtilityClass;

import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EntityMappers {

    public Expense toExpense(ExpenseEntity expense) {
        return Expense.builder().id(expense.getId()).category(expense.getCategory()).amount(expense.getAmount())
                .createdOn(expense.getCreatedOn()).description(expense.getDescription())
                .user(toUserDTO(expense.getUser())).build();
    }

    public ExpenseEntity toExpenseEntity(Expense dto) {
        return ExpenseEntity.builder().id(dto.getId()).category(dto.getCategory()).createdOn(dto.getCreatedOn())
                .description(dto.getDescription()).amount(dto.getAmount()).user(toUserEntity(dto.getUser())).build();
    }

    public UserEntity toUserEntity(User user) {
        UserEntity userEntity = UserEntity.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
                .createdOn(user.getCredatedOn()).name(user.getName()).build();

        if (!CollectionUtils.isEmpty(user.getExpenses())) {
            List<ExpenseEntity> expenseList = new ArrayList<>();
            user.getExpenses().forEach(expenseDto -> expenseList.add(toExpenseEntity(expenseDto)));
            userEntity.setExpenses(expenseList);
        }
        return userEntity;
    }

    public User toUserDTO(UserEntity user) {
        return new User(user.getId(), user.getEmail(), user.getPassword(), user.getName(), new ArrayList<>(),
                user.getCreatedOn(), user.getUpdateOn(), user.getVersion());
    }
}
