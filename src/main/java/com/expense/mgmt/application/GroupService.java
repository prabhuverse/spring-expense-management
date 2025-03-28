package com.expense.mgmt.application;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.Group;
import com.expense.mgmt.domain.model.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository repository;

    public Group createGroup(Group group) {
        log.info("Creating group {}", group);
        return repository.save(group);
    }

    public List<Group> listGroups() {
        return repository.listGroups().get();
    }

    public Group getGroupById(@NonNull Long id) {
        return repository.getById(id).get();
    }

    public Group getGroupByName(@NonNull String name) {
        return repository.getByName(name).get();
    }

    public Group deleteGroupById(@NonNull Long id) {
        Group group = getGroupById(id);
        repository.delete(id);
        return group;
    }
}
