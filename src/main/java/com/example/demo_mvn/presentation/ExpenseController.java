package com.example.demo_mvn.presentation;


import com.example.demo_mvn.application.ExpenseService;
import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.domain.model.ExpenseCategory;
import com.example.demo_mvn.presentation.rest.ApiResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;


	@RequestMapping(method = RequestMethod.POST, path = "/create")
	public ResponseEntity<ApiResponse<ExpenseDTO>> createExpense(@RequestBody ExpenseDTO expenseDTO) {
		log.info("Received new expense request {}", expenseDTO);
		try {
			ExpenseDTO createdExpense = expenseService.createExpense(expenseDTO);
			if (createdExpense == null) {
				// Assuming null means creation failed due to invalid data
				ApiResponse<ExpenseDTO> errorResponse = new ApiResponse<>(HttpStatus.UNPROCESSABLE_ENTITY,
						"Failed to create expense due to invalid input.", createdExpense);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
			}
			ApiResponse<ExpenseDTO> successResponse =
					new ApiResponse<>(HttpStatus.CREATED, "Expense created successfully", createdExpense);
			return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
		} catch (Exception e) {
			// Handle unexpected exceptions
			ApiResponse<ExpenseDTO> errorResponse = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR,
					"Internal server error: " + e.getMessage(), null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/list/{category}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<List<ExpenseDTO>>> listExpenseByType(@PathVariable @NonNull String category) {
		log.info("Fetch expenses by category {}", category);
		List<ExpenseDTO> expenseDTOS =
				expenseService.listExpenseByType(ExpenseCategory.valueOf(category.toUpperCase()));
		ApiResponse<List<ExpenseDTO>> response = new ApiResponse<>(HttpStatus.OK, "Expenses Fetched", expenseDTOS);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<ExpenseDTO>> getExpenseById(@PathVariable @NonNull Long id) {
		log.info("Fetch expense by id {}", id);
		ExpenseDTO expenseDto = expenseService.getExpenseById(id);
		if (expenseDto == null) {
			return ResponseEntity.notFound().build();
		}
		ApiResponse<ExpenseDTO> response = new ApiResponse<>(HttpStatus.OK, "Expense Fetched", expenseDto);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<ExpenseDTO>> deleteExpense(@PathVariable Long id) {
		ExpenseDTO expenseDTO = expenseService.deleteExpense(id);
		if (expenseDTO == null) {
			return ResponseEntity.notFound().build();
		}
		ApiResponse<ExpenseDTO> response = new ApiResponse<>(HttpStatus.OK, "Expense deleted", expenseDTO);
		return ResponseEntity.accepted().body(response);
	}
}
