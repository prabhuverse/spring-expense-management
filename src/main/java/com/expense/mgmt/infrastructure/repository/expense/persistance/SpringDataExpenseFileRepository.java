package com.expense.mgmt.infrastructure.repository.expense.persistance;

import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataExpenseFileRepository extends JpaRepository<ExpenseFileEntity,Long> {
}
