package com.expense.mgmt.application;

import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.domain.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// application service - orchestrates/delegates to the domain layer
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerUser(final User user) {
        userRepository.save(user);
        log.info("Persisted user object {}", user);
        return user;
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email).get();
    }

    public User findUserById(final Long id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(final User userDTO) {
        User currentUser = userRepository.findByEmail(userDTO.getEmail()).get();
        if (StringUtils.isNotBlank(userDTO.getName()))
            currentUser.setName(userDTO.getName());
        if (StringUtils.isNotBlank(userDTO.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        log.info("Current User Before updating {} and user {}", currentUser, userDTO);
        return userRepository.save(currentUser);
    }
}
