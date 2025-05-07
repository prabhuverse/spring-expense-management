package com.expense.mgmt.domain.model.dto.expense;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ExpenseCategoryResponse {

    private long totalElements;

    private int totalPages;

    private boolean hasNext;

    private List<Expense> expenses;

}
