package com.example.demo_mvn.domain.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

// @Data
@Entity(name = "expenses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ExpenseCategory category;

	@CreationTimestamp
	@Column(name = "createdOn", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private LocalDate createdOn;

	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal amount;


	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


}
