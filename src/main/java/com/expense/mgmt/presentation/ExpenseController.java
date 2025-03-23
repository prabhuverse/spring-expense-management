package com.expense.mgmt.presentation;

import com.expense.mgmt.application.ExpenseService;
import com.expense.mgmt.application.dto.ExpenseDTO;
import com.expense.mgmt.domain.model.ExpenseCategory;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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

    @RequestMapping(method = RequestMethod.GET, path = "/list/{category}", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public List<ExpenseDTO> listExpenseByType(@PathVariable @NonNull String category) {
        log.info("Fetch expenses by category {}", category);
        List<ExpenseDTO> expenseDTOS =
                expenseService.listExpenseByType(ExpenseCategory.valueOf(category.toUpperCase()));
        return expenseDTOS;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ExpenseDTO getExpenseById(@PathVariable @NonNull Long id) {
        log.info("Fetch expense by id {}", id);
        return expenseService.getExpenseById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ExpenseDTO deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }
}
