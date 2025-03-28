package com.expense.mgmt.application;

import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.domain.model.dto.ExpenseFile;
import com.expense.mgmt.domain.model.dto.spring.FileInfo;
import com.expense.mgmt.domain.model.repository.ExpenseFileObjectRepository;
import com.expense.mgmt.domain.model.repository.ExpenseFileRepository;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    private final ExpenseFileObjectRepository fileObjectRepository;

    private final ExpenseFileRepository fileRepository;

    private final UserService userService;

    public Mono<Expense> createExpense(Expense expense) {
        ExpenseEntity expenseEntity = EntityMappers.toExpenseEntity(expense);
        return userService.findUserById(expenseEntity.getUser().getId())
                .map((user -> {
                    expenseEntity.setUser(EntityMappers.toUserEntity(user));
                    return expenseEntity;
                }))
                .flatMap(exp -> repository.save(exp));

    }


    //@Transactional
    public Mono<Expense> uploadFile(final Long expenseId, final MultipartFile file) {
        return getExpenseById(expenseId)
                .flatMap(expense -> {
                    String fileName = expense.getUser().getId() + "/" + file.getOriginalFilename();
                    return fileObjectRepository.uploadFile(fileName, file)
                            .flatMap(fileInfo -> {
                                ExpenseFile expenseFile = ExpenseFile.builder().fileInfo(fileInfo).build();
                                return fileRepository.save(expenseFile)
                                        .flatMap(savedFile -> {
                                            expense.setExpenseFile(savedFile);
                                            return createExpense(expense);
                                        });
                            });
                });

    }

    public Flux<Expense> listExpenseByType(ExpenseCategory category) {
        return repository.findByCategory(category);
    }

    public Mono<Expense> deleteExpense(Long id) {
        return repository.findById(id);
    }

    public Mono<Expense> getExpenseById(Long id) {
        return repository.findById(id);
    }
}
