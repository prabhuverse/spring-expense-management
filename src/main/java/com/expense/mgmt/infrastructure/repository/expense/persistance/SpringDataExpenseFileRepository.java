package com.expense.mgmt.infrastructure.repository.expense.persistance;

import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseFileEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataExpenseFileRepository extends ReactiveCrudRepository<ExpenseFileEntity,Long> {
}
