package com.expense.mgmt.infrastructure.repository.user.persistance;

import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {

}
