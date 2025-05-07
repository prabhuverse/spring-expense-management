package com.expense.mgmt.domain.model.dto.expense;

import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.dto.Group;
import com.expense.mgmt.domain.model.dto.User;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Expense {
    private Long id;

    private ExpenseCategory category;

    private String description;

    private BigDecimal amount;

    private LocalDate createdOn;

    private User user;

    private ExpenseFile expenseFile;

    private Integer version;

    private Group group;

}
