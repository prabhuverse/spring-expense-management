package com.expense.mgmt.infrastructure.repository.group.persistance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.Group;
import com.expense.mgmt.domain.model.repository.GroupRepository;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaGroupRepository implements GroupRepository {

    private final SpringReactiveJpaGroupRepository groupRepository;

    @Override
    public Mono<Group> save(Group group) {
        return groupRepository.save(EntityMappers.toGroupEntity(group)).map(EntityMappers::toGroup);
    }

    @Override
    public Flux<Group> listGroups() {
        return groupRepository.findAll().map(EntityMappers::toGroup);
    }

    @Override
    public Mono<Group> getById(Long id) {
        return groupRepository.findById(id).map(EntityMappers::toGroup)
                .doOnError((e) -> log.error("unable to get group by id {} cause ", id, e));
    }

    @Override
    public Mono<Group> getByName(String name) {
        return groupRepository.findByName(name).map(EntityMappers::toGroup)
                .doOnError((e) -> log.error("unable to get group by name {} cause ", name, e));
    }
}
