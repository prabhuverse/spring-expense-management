package com.expense.mgmt.domain.model.dto;

import com.expense.mgmt.domain.model.dto.spring.FileInfo;

import java.time.LocalDateTime;

public record ExpenseFile(Long id, FileInfo fileInfo, Integer version, LocalDateTime createdOn,
                          LocalDateTime updatedOn) {
}
