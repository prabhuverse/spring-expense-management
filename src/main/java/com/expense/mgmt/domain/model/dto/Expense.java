package com.expense.mgmt.domain.model.dto;

import com.expense.mgmt.domain.model.ExpenseCategory;

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
}
