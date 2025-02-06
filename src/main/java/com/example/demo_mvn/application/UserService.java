package com.example.demo_mvn.application;

import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.application.mapper.UserMapper;
import com.example.demo_mvn.domain.model.User;
import com.example.demo_mvn.domain.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// application service - orchestrates/delegates to the domain layer
@Slf4j
@Service
public class UserService {


	@Autowired
	UserRepository userRepository;

	@Autowired
	UserMapper userMapper;

	public Optional<UserDTO> registerUser(UserDTO userDTO) {
		Optional<UserDTO> userResponse = Optional.empty();
		User user = userMapper.toUser(userDTO);
		try {
			user = userRepository.save(user);
			log.info("Persisted user object {}", user);
			userResponse = Optional.of(userMapper.toUserDTO(user));
		} catch (DataIntegrityViolationException e) {
			log.error("unable to persist user object {} cause ", user, e);
		}
		return userResponse;
	}

	public UserDTO findUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent())
			return userMapper.toUserDTO(user.get());
		return null;
	}

	public User findUserById(Long id) {
		return userRepository.findById(id).get();
	}

	public UserDTO updateUser(UserDTO userDTO) {
		User currentUser = userRepository.findByEmail(userDTO.email()).get();
		if (StringUtils.isNotBlank(userDTO.name()))
			currentUser.setName(userDTO.name());
		if (StringUtils.isNotBlank(userDTO.password()))
			currentUser.setPassword(userDTO.password());
		log.info("Current User Before updating {} and useroj {}", currentUser, userDTO);
		User updateUser = userRepository.save(currentUser);
		return userMapper.toUserDTO(updateUser);
	}
}
