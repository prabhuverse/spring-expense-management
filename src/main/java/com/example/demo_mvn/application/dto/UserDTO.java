package com.example.demo_mvn.application.dto;

import com.example.demo_mvn.infrastructure.spring.annotation.MaskField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDTO {

	private Long id;

	private String email;

	@MaskField
	private String password;

	private String name;

	private List<ExpenseDTO> expenses;

	private LocalDateTime credatedOn;

	private LocalDateTime updatedOn;

	private Integer version;
}
