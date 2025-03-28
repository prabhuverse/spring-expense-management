package com.expense.mgmt.domain.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record Group(Long id, String name, LocalDateTime createdOn, List<Expense> expenses, Integer version) {
}
