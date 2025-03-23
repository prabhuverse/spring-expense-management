package com.expense.mgmt.infrastructure.repository.expense.persistance;


import com.expense.mgmt.infrastructure.repository.persistance.expense.Expense;
import com.expense.mgmt.domain.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(ExpenseCategory category);
}
