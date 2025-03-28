package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.Group;

import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GroupRepository {

    Mono<Group> save(Group group);

    Flux<Group> listGroups();

    Mono<Group> getById(Long id);

    Mono<Group> getByName(String name);
}
