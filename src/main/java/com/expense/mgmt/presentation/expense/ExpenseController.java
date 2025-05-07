package com.expense.mgmt.presentation.expense;

import com.expense.mgmt.application.ExpenseService;
import com.expense.mgmt.domain.model.dto.expense.Expense;
import com.expense.mgmt.domain.model.ExpenseCategory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.expense.ExpenseCategoryRequest;
import com.expense.mgmt.domain.model.dto.expense.ExpenseCategoryResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @RequestMapping(method = RequestMethod.POST, path = "/create", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
    })
    public Expense createExpense(@RequestBody(required = false) Expense expense) {
        log.info("Received new expense request {}", expense);
        return expenseService.createExpense(expense);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/list/{category}", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ExpenseCategoryResponse listExpenseByType(@ModelAttribute ExpenseCategoryRequest request) {
        log.info("Fetch expenses by category {}", request.category());
        return expenseService.listExpenseByType(request);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Expense getExpenseById(@PathVariable @NonNull Long id) {
        log.info("Fetch expense by id {}", id);
        return expenseService.getExpenseById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Expense deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }
}
