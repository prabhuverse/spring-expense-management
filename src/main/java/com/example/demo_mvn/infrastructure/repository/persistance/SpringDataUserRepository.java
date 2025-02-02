package com.example.demo_mvn.infrastructure.repository.persistance;


import com.example.demo_mvn.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
