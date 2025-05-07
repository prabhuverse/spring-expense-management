package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.expense.ExpenseFile;

import java.util.List;
import java.util.Optional;

public interface ExpenseFileRepository {

    ExpenseFile save(ExpenseFile expense);

    Optional<ExpenseFile> findById(Long id);

    List<ExpenseFile> findAll();


}
