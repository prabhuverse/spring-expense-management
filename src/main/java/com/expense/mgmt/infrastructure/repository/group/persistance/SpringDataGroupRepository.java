package com.expense.mgmt.infrastructure.repository.group.persistance;

import aj.org.objectweb.asm.commons.Remapper;
import com.expense.mgmt.infrastructure.repository.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataGroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByName(String name);
}
