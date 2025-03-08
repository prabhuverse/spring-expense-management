package com.example.demo_mvn.application.dto;

import com.example.demo_mvn.domain.model.ExpenseCategory;

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
public class ExpenseDTO {
    private Long id;
    private ExpenseCategory category;
    private String description;
    private BigDecimal amount;
    private LocalDate createdOn;
    private UserDTO user;
}
