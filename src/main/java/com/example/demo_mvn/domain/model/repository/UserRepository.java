package com.example.demo_mvn.domain.model.repository;

import com.example.demo_mvn.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    List<User> findAll();
}

