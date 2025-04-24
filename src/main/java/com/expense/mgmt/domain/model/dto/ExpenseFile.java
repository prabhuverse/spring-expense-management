package com.expense.mgmt.domain.model.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExpenseFile(Long id, FileInfo fileInfo, Integer version, LocalDateTime createdOn,
                          LocalDateTime updatedOn) {
}
