package com.example.demo_mvn.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public record UserDTO(Long id, String email, String password, String name, List<ExpenseDTO> expenses,
		LocalDateTime credatedOn, LocalDateTime updatedOn, Integer version) {

	// public UserDTO(){
	// this(0L,"test.test.com","test@123","Test Expense",null,LocalDate.now());
	// }
}
