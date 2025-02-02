package com.example.demo_mvn.application.dto;

import java.time.LocalDate;
import java.util.List;

public record UserDTO(Long id, String email, String password, String name, List<ExpenseDTO> expenses, LocalDate credatedOn) {
}
