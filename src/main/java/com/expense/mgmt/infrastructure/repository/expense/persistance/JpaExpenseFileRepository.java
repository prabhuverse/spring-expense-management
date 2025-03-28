package com.expense.mgmt.infrastructure.repository.expense.persistance;

import lombok.RequiredArgsConstructor;

import com.expense.mgmt.domain.model.dto.ExpenseFile;
import com.expense.mgmt.domain.model.repository.ExpenseFileRepository;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseFileEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaExpenseFileRepository implements ExpenseFileRepository {

    private final SpringDataExpenseFileRepository repository;

    @Override
    public Mono<ExpenseFile> save(ExpenseFile expense) {
        return repository.save(EntityMappers.toExpenseFileEntity(expense)).map(EntityMappers::toExpenseFile);
    }

    @Override
    public Mono<ExpenseFile> findById(Long id) {
        return repository.findById(id).map(EntityMappers::toExpenseFile);
    }

    @Override
    public Flux<ExpenseFile> findAll() {
        return repository.findAll().map(EntityMappers::toExpenseFile);
    }
}
