package com.expense.mgmt.application;

import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.domain.model.dto.ExpenseFile;
import com.expense.mgmt.domain.model.dto.FileInfo;
import com.expense.mgmt.domain.model.dto.FileStreamInfo;
import com.expense.mgmt.domain.model.repository.ExpenseFileObjectRepository;
import com.expense.mgmt.domain.model.repository.ExpenseFileRepository;
import com.expense.mgmt.infrastructure.repository.entity.EntityMappers;
import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.domain.model.repository.ExpenseRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    private final ExpenseFileObjectRepository fileObjectRepository;

    private final ExpenseFileRepository fileRepository;

    private final UserService userService;

    public Expense createExpense(Expense expense) {
        ExpenseEntity expenseEntity = EntityMappers.toExpenseEntity(expense);
        expenseEntity.setUser(EntityMappers.toUserEntity(userService.findUserById(expense.getUser().getId())));
        expense = repository.save(expenseEntity);
        log.info("Persisted expense object {}", expense);
        return expense;
    }


    @Transactional
    public Expense uploadFile(final Long expenseId, final MultipartFile file) {
        Expense expense = getExpenseById(expenseId);
        if (expense != null) {
            String fileName = expense.getUser().getId() + "/" + file.getOriginalFilename();
            FileInfo fileInfo = fileObjectRepository.uploadFile(fileName, file);
            ExpenseFile expenseFile = ExpenseFile.builder().fileInfo(fileInfo).build();
            expenseFile = fileRepository.save(expenseFile);
            expense.setExpenseFile(expenseFile);
            createExpense(expense);
        } else {
            throw new RuntimeException("Expense Not found");
        }
        return expense;
    }

    public List<Expense> listExpenseByType(ExpenseCategory category) {
        return repository.findByCategory(category);
    }

    public Expense deleteExpense(Long id) {
        return repository.findById(id).get();
    }

    public Expense getExpenseById(Long id) {
        return repository.findById(id).get();
    }

    public Optional<FileStreamInfo> downloadFile(@NonNull String path) {
        return Optional.of(fileObjectRepository.downloadFile(path));
    }

    public Mono<FileStreamInfo> downloadFileAsync(@NonNull String path) {
        return fileObjectRepository.downloadFileAsync(path);
    }
}
