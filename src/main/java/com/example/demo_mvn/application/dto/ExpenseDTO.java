package com.example.demo_mvn.application.dto;

import com.example.demo_mvn.domain.model.ExpenseCategory;
import com.example.demo_mvn.domain.model.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExpenseDTO {
	private Long id;
	private ExpenseCategory category;
	private String description;
	private BigDecimal amount;
	private LocalDate createdOn;
	private UserDTO user;
}
