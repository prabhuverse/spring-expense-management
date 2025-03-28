package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.ExpenseFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ExpenseFileRepository {

    Mono<ExpenseFile> save(ExpenseFile expense);

    Mono<ExpenseFile> findById(Long id);

    Flux<ExpenseFile> findAll();


}
