package com.expense.mgmt.infrastructure.repository.persistance;


import com.expense.mgmt.domain.model.Expense;
import com.expense.mgmt.domain.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(ExpenseCategory category);
}
