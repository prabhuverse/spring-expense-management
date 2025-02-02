package com.example.demo_mvn.application;

import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.application.mapper.UserMapper;
import com.example.demo_mvn.domain.model.User;
import com.example.demo_mvn.domain.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	public UserDTO registerUser(UserDTO userDTO) {
		User user = userMapper.toUser(userDTO);
		user = userRepository.save(user);
		return userMapper.toUserDTO(user);
	}

	public UserDTO findUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isPresent())
			return userMapper.toUserDTO(user.get());
		return null;
	}

}
