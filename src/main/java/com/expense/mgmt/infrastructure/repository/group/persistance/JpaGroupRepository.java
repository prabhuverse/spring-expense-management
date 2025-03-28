package com.expense.mgmt.infrastructure.repository.group.persistance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.Group;
import com.expense.mgmt.domain.model.repository.GroupRepository;
import com.expense.mgmt.infrastructure.repository.entity.EntityMappers;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class JpaGroupRepository implements GroupRepository {

    private final SpringDataGroupRepository groupRepository;

    @Override
    public Group save(Group group) {
        return EntityMappers.toGroup(groupRepository.save(EntityMappers.toGroupEntity(group)));
    }

    @Override
    public Optional<List<Group>> listGroups() {
        return Optional.of(groupRepository.findAll().stream().map(EntityMappers::toGroup).toList());
    }

    @Override
    public Optional<Group> getById(Long id) {
        return groupRepository.findById(id).map(grp -> {
            if (grp != null) {
                return EntityMappers.toGroup(grp);
            }
            return null;
        });
    }

    @Override
    public Optional<Group> getByName(String name) {
        return groupRepository.findByName(name).map(grp -> {
            if (grp != null) {
                return EntityMappers.toGroup(grp);
            }
            return null;
        });
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
