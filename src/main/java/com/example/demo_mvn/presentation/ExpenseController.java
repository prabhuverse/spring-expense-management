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
	public ExpenseDTO createExpense(@RequestBody ExpenseDTO expenseDTO) {
		log.info("Received new expense request {}", expenseDTO);
		return expenseService.createExpense(expenseDTO);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/list/{category}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<List<ExpenseDTO>>> listExpenseByType(@PathVariable @NonNull String category) {
		log.info("Fetch expenses by category {}", category);
		List<ExpenseDTO> expenseDTOS = expenseService.listExpenseByType(ExpenseCategory.valueOf(category.toUpperCase()));
		ApiResponse<List<ExpenseDTO>> response = new ApiResponse<>(HttpStatus.OK, "Expenses Fetched", expenseDTOS);
		return ResponseEntity.ok(response);
	}

}
