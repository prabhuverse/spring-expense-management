package com.example.demo_mvn.application;

import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.application.mapper.EntityMappers;
import com.example.demo_mvn.domain.model.User;
import com.example.demo_mvn.domain.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// application service - orchestrates/delegates to the domain layer
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Optional<UserDTO> registerUser(final UserDTO userDTO) {
        Optional<UserDTO> userResponse = Optional.empty();
        User user = EntityMappers.toUserEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        try {
            user = userRepository.save(user);
            log.info("Persisted user object {}", user);
            userResponse = Optional.of(EntityMappers.toUserDTO(user));
        } catch (DataIntegrityViolationException e) {
            log.error("unable to persist user object {} cause ", user, e);
            throw e;
        }
        return userResponse;
    }

    public UserDTO findUserByEmail(final String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return EntityMappers.toUserDTO(user.get());
        return null;
    }

    public User findUserById(final Long id) {
        return userRepository.findById(id).get();
    }

    public UserDTO updateUser(final UserDTO userDTO) {
        User currentUser = userRepository.findByEmail(userDTO.getEmail()).get();
        if (StringUtils.isNotBlank(userDTO.getName()))
            currentUser.setName(userDTO.getName());
        if (StringUtils.isNotBlank(userDTO.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        log.info("Current User Before updating {} and user {}", currentUser, userDTO);
        User updateUser = userRepository.save(currentUser);
        return EntityMappers.toUserDTO(updateUser);
    }
}
