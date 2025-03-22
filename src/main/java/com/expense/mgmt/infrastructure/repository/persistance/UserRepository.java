package com.expense.mgmt.infrastructure.repository.persistance;

import com.expense.mgmt.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
