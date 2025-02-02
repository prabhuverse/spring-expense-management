package com.example.demo_mvn.presentation;


import com.example.demo_mvn.application.ExpenseService;
import com.example.demo_mvn.application.dto.ExpenseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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

}
