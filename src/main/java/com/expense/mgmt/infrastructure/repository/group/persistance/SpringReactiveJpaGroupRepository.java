package com.expense.mgmt.infrastructure.repository.group.persistance;

import com.expense.mgmt.infrastructure.repository.persistance.group.GroupEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface SpringReactiveJpaGroupRepository extends ReactiveCrudRepository<GroupEntity, Long> {

    Mono<GroupEntity> findByName(String name);

}
