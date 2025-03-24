package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAll();
}

