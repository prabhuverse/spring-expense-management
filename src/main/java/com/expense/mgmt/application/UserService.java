package com.expense.mgmt.application;

import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.domain.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

// application service - orchestrates/delegates to the domain layer
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Mono<User> registerUser(final User user) {
        log.info("Persisted user object {}", user);
        return userRepository.save(user);
    }

    public Mono<User> findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<User> findUserById(final Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> updateUser(final User userDTO) {
        return userRepository.findByEmail(userDTO.getEmail())
                .map((user -> {
                    if (StringUtils.isNotBlank(userDTO.getName()))
                        user.setName(userDTO.getName());
                    if (StringUtils.isNotBlank(userDTO.getPassword())) {
                        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                    }
                    return user;
                })).flatMap(user -> userRepository.save(user));
    }
}
