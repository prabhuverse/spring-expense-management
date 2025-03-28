package com.expense.mgmt.infrastructure.repository.user.persistance;


import lombok.RequiredArgsConstructor;

import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import com.expense.mgmt.domain.model.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// implements the technical details
@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<User> save(User user) {
        UserEntity entity = EntityMappers.toUserEntity(user);
        if (StringUtils.isNotBlank(user.getPassword())) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(entity).map(EntityMappers::toUserDTO);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(EntityMappers::toUserDTO);
    }

    @Override
    public Mono<User> findById(Long id) {
        return userRepository.findById(id).map(EntityMappers::toUserDTO);
    }

    @Override
    public Flux<User> findAll() {
        return userRepository.findAll().map(EntityMappers::toUserDTO);
    }
}
