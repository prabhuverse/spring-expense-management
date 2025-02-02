package com.example.demo_mvn.infrastructure.repository.persistance;

import com.example.demo_mvn.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
