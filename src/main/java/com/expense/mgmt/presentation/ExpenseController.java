package com.expense.mgmt.presentation;

import com.expense.mgmt.application.ExpenseService;
import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.domain.model.ExpenseCategory;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<Expense> createExpense(@RequestBody(required = false) Expense expense) {
        log.info("Received new expense request {}", expense);
        return expenseService.createExpense(expense);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload/{expenseId}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public Mono<Expense> uploadFile(@PathVariable("expenseId") Long expenseId, @RequestParam("file") MultipartFile file) {
        return expenseService.uploadFile(expenseId, file);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list/{category}", produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public Flux<Expense> listExpenseByType(@PathVariable @NonNull String category) {
        log.info("Fetch expenses by category {}", category);
        return expenseService.listExpenseByType(ExpenseCategory.valueOf(category.toUpperCase()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Expense> getExpenseById(@PathVariable @NonNull Long id) {
        log.info("Fetch expense by id {}", id);
        return expenseService.getExpenseById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Expense> deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }
}
