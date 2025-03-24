package com.expense.mgmt.infrastructure.repository.user.persistance;


import lombok.RequiredArgsConstructor;

import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import com.expense.mgmt.domain.model.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// implements the technical details
@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        UserEntity entity = EntityMappers.toUserEntity(user);
        if (StringUtils.isNotBlank(user.getPassword())) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        entity = userRepository.save(entity);
        return EntityMappers.toUserDTO(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);
        if (entity.isPresent()) {
            return Optional.of(EntityMappers.toUserDTO(userRepository.findByEmail(email).get()));
        }
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(EntityMappers.toUserDTO(userRepository.findById(id).get()));
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream().map(EntityMappers::toUserDTO).toList();
    }
}
