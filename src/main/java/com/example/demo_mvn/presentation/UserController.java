package com.example.demo_mvn.presentation;


import com.example.demo_mvn.application.UserService;
import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.presentation.rest.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
// handles the API requests (acts as ports), interactions
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

	// application service - orchestrates/delegates to the domain layer
	@Autowired
	private UserService userService;


	@RequestMapping(method = RequestMethod.POST, path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<UserDTO>> registerUser(@RequestBody UserDTO userDTO) {
		log.info("User register request recieved {}", userDTO);
		Optional<UserDTO> resp = userService.registerUser(userDTO);
		ApiResponse<UserDTO> response = null;
		if (resp.isPresent()) {
			response = new ApiResponse<>(HttpStatus.CREATED, "user created", userDTO);
		} else {
			response = new ApiResponse<>(HttpStatus.OK, "user not created", userDTO);
		}
		return ResponseEntity.ok(response);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(@PathVariable String email) {
		log.info("Get Userbyemail request {}", email);
		UserDTO userDTO = userService.findUserByEmail(email);
		ApiResponse<UserDTO> response = new ApiResponse<>(HttpStatus.OK, "user fetched", userDTO);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
