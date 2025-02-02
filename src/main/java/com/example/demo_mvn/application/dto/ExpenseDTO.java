package com.example.demo_mvn.application.dto;

import com.example.demo_mvn.domain.model.ExpenseCategory;
import com.example.demo_mvn.domain.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDTO(Long id, ExpenseCategory category, String description, BigDecimal amount, LocalDate createdOn,
		User user) {
}
