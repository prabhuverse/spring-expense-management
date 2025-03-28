package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {

    Group save(Group group);

    Optional<List<Group>> listGroups();

    Optional<Group> getById(Long id);

    Optional<Group> getByName(String name);

    void delete(Long id);

}
