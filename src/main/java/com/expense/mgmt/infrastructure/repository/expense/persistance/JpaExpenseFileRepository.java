package com.expense.mgmt.infrastructure.repository.expense.persistance;

import lombok.RequiredArgsConstructor;

import com.expense.mgmt.domain.model.dto.ExpenseFile;
import com.expense.mgmt.domain.model.repository.ExpenseFileRepository;
import com.expense.mgmt.infrastructure.repository.entity.EntityMappers;
import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseFileEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JpaExpenseFileRepository implements ExpenseFileRepository {

    private final SpringDataExpenseFileRepository repository;

    @Override
    public ExpenseFile save(ExpenseFile expense) {
        ExpenseFileEntity entity = repository.saveAndFlush(EntityMappers.toExpenseFileEntity(expense));
        return EntityMappers.toExpenseFile(entity);
    }

    @Override
    public Optional<ExpenseFile> findById(Long id) {
        Optional<ExpenseFileEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(EntityMappers.toExpenseFile(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<ExpenseFile> findAll() {
        List<ExpenseFileEntity> entities = repository.findAll();
        return entities.stream().map(EntityMappers::toExpenseFile).toList();
    }
}
