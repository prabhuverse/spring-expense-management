package com.example.demo_mvn.infrastructure.repository.persistance;


import com.example.demo_mvn.domain.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);
}
