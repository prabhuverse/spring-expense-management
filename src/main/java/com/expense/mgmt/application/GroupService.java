package com.expense.mgmt.application;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.Group;
import com.expense.mgmt.domain.model.repository.GroupRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;

    public Mono<Group> createGroup(final Group group) {
        return repository.save(group);
    }

    public Flux<Group> listGroups() {
        return repository.listGroups();
    }

    public Mono<Group> getGroupById(@NonNull final Long id) {
        return repository.getById(id);
    }

    public Mono<Group> findGroupByName(@NonNull final String name) {
        return repository.getByName(name);
    }
}
