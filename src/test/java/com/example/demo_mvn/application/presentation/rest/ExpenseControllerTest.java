package com.example.demo_mvn.application.presentation.rest;

import com.example.demo_mvn.application.dto.ExpenseDTO;
import com.example.demo_mvn.presentation.ExpenseController;
import com.example.demo_mvn.presentation.rest.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExpenseControllerTest {

    @Mock
    ExpenseController expenseController;

    @Test
    public void test_createExpense(){
        ExpenseDTO expenseDTO = mock(ExpenseDTO.class);
        ResponseEntity<ApiResponse<ExpenseDTO>> response = mock(ResponseEntity.class);
        when(expenseController.createExpense(expenseDTO)).thenReturn(response);

        System.out.println("tested...");
    }
}
