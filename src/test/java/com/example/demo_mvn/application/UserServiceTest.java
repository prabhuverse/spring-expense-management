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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserMapper userMapper;

	@InjectMocks
	private ExpenseMapper expenseMapper;

	@InjectMocks
	private UserService userService;

	private User mockUser;

	private UserDTO mockUserDTO;

	@BeforeEach
	void setUp() {
		System.out.println("Executing ");
		userMapper.expenseMapper = expenseMapper;
		mockUser = User.builder().id(1L).name("Test Expense").email("test@gmail.com").password("test@123")
				.createdOn(LocalDate.now()).build();
		mockUserDTO = userMapper.toUserDTO(mockUser);
		userService.userMapper = userMapper;
	}

	@Test
	void test_registerUser() {

		// Mock behavior of mapping
		when(userMapper.toUser(any(UserDTO.class))).thenReturn(mockUser); // Ensure mapping works
		when(userRepository.save(any(User.class))).thenReturn(mockUser); // Ensure repo returns saved user
		when(userMapper.toUserDTO(any(User.class))).thenReturn(mockUserDTO); // Ensure mapping works

		// Call the method
		UserDTO savedUser = userService.registerUser(mockUserDTO);
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
