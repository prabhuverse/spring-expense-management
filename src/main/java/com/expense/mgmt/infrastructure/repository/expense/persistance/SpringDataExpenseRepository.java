package com.expense.mgmt.infrastructure.repository.expense.persistance;


import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface SpringDataExpenseRepository extends ReactiveCrudRepository<ExpenseEntity, Long> {

    Flux<ExpenseEntity> findByCategory(ExpenseCategory category);
}
