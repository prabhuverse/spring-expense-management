package com.expense.mgmt.domain.model.dto;

import com.expense.mgmt.infrastructure.spring.annotation.MaskField;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    @MaskField
    private String password;

    private String name;

    private List<Expense> expenses;

    private LocalDateTime credatedOn;

    private LocalDateTime updatedOn;

    private LocalDate dateOfBirth;

    private Integer version;
}
