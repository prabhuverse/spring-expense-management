package com.expense.mgmt.infrastructure.repository.user.persistance;


import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends ReactiveCrudRepository<UserEntity, Long> {

    Mono<UserEntity> findByEmail(String email);

}
