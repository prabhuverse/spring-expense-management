package com.expense.mgmt.infrastructure.repository.persistance;

import lombok.experimental.UtilityClass;

import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.domain.model.dto.ExpenseFile;
import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseFileEntity;
import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class EntityMappers {

    public Expense toExpense(ExpenseEntity expense) {
         return Expense.builder()
                .id(expense.getId())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .createdOn(expense.getCreatedOn())
                .description(expense.getDescription())
                .user(toUserDTO(expense.getUser()))
                .version(expense.getVersion())
                .expenseFile(toExpenseFile(expense.getFile()))
                .build();
    }

    public ExpenseEntity toExpenseEntity(Expense dto) {
        return ExpenseEntity.builder()
                .id(dto.getId())
                .category(dto.getCategory())
                .createdOn(dto.getCreatedOn())
                .description(dto.getDescription())
                .amount(dto.getAmount())
                .user(toUserEntity(dto.getUser()))
                .version(dto.getVersion())
                .file(toExpenseFileEntity(dto.getExpenseFile()))
                .build();
    }

    public UserEntity toUserEntity(User user) {
        UserEntity userEntity = UserEntity.builder().id(user.getId()).email(user.getEmail()).password(user.getPassword())
                .createdOn(user.getCredatedOn()).name(user.getName()).version(user.getVersion()).build();

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

    public static ExpenseFile toExpenseFile(ExpenseFileEntity expense) {
        if (expense == null) {
            return null;
        }
        return ExpenseFile.builder()
                .version(expense.getVersion())
                .updatedOn(expense.getUpdatedOn())
                .createdOn(expense.getCreatedOn())
                .fileInfo(expense.getInfo())
                .id(expense.getId())
                .build();
    }

    public static ExpenseFileEntity toExpenseFileEntity(ExpenseFile expense) {
        if (expense == null) {
            return null;
        }
        return ExpenseFileEntity.builder()
                .version(expense.version())
                .updatedOn(expense.updatedOn())
                .createdOn(expense.createdOn())
                .info(expense.fileInfo())
                .id(expense.id())
                .build();
    }
}
