package com.expense.mgmt.domain.model.dto.expense;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.lang.NonNull;

public record ExpenseCategoryRequest(@NonNull String category, @DefaultValue(value = "0") Integer page,
                                     @DefaultValue(value = "10") Integer size) {
}
