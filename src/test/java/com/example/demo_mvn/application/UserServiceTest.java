package com.example.demo_mvn.application;

import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.application.mapper.ExpenseMapper;
import com.example.demo_mvn.application.mapper.UserMapper;
import com.example.demo_mvn.domain.model.User;
import com.example.demo_mvn.domain.model.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest  {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ExpenseMapper expenseMapper;

	@Autowired
	private UserService userService;

	private User mockUser;

	private UserDTO mockUserDTO;

	@BeforeEach
	void setUp() {
		System.out.println("Executing ");
		mockUser = User.builder().id(1L).name("Test Expense").email("test@gmail.com").password("test@123")
				.createdOn(LocalDateTime.now()).build();
		//mockUserDTO = new UserDTO();
		// userMapper.expenseMapper = expenseMapper;
		// when(userMapper.toUserDTO(any(User.class))).thenReturn(mockUserDTO);
		mockUserDTO = userMapper.toUserDTO(mockUser);
		// userService.userMapper = userMapper
	}

	@Disabled
	@Test
	void test_registerUser() {

		// Mock behavior of mapping
		// when(userMapper.toUser(any(UserDTO.class))).thenReturn(mockUser); // Ensure mapping works
		// when(userRepository.save(any(User.class))).thenReturn(mockUser); // Ensure repo returns saved user
		// when(userMapper.toUserDTO(any(User.class))).thenReturn(mockUserDTO); // Ensure mapping works

		// Call the method
		UserDTO savedUser = userService.registerUser(mockUserDTO).get();
		log.info("Object saved {}", savedUser);
		// assertNotNull(savedUser);
		assertEquals(1L, savedUser.id());
		assertEquals("test@gmail.com", savedUser.email());
		// assertNotEquals(LocalDate.now(), savedUser.credatedOn());

	}

	@Disabled
	@Test
	void test_findUserByEmail() {
		// Call the method
		UserDTO savedUser = userService.findUserByEmail("test@gmail.com");

		// assertNotNull(savedUser);
		assertEquals(1L, savedUser.id());
		assertEquals("test@gmail.com", savedUser.email());
		// assertNotEquals(LocalDate.now(), savedUser.credatedOn());

	}
}
