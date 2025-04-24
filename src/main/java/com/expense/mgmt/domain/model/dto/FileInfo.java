package com.expense.mgmt.domain.model.dto;

import lombok.Builder;

@Builder
public record FileInfo(String name, String path, String type, Integer size) {
}
