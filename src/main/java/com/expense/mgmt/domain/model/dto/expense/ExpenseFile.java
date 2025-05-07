package com.expense.mgmt.domain.model.dto.expense;

import lombok.Builder;

import com.expense.mgmt.domain.model.dto.FileInfo;

import java.time.LocalDateTime;

@Builder
public record ExpenseFile(Long id, FileInfo fileInfo, Integer version, LocalDateTime createdOn,
                          LocalDateTime updatedOn) {
}
