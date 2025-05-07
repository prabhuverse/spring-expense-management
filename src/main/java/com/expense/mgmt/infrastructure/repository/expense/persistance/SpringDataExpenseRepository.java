package com.expense.mgmt.infrastructure.repository.expense.persistance;


import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
import com.expense.mgmt.domain.model.ExpenseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpringDataExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    Page<ExpenseEntity> findByCategory(ExpenseCategory category, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE ExpenseEntity e SET e.file.id = :fileId WHERE e.id = :expenseId")
    int updateExpenseFile(@Param("expenseId") Long expenseId, @Param("fileId") Long fileId);

}
