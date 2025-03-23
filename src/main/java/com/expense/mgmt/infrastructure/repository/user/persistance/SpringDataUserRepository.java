package com.expense.mgmt.infrastructure.repository.user.persistance;


import com.expense.mgmt.infrastructure.repository.persistance.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
