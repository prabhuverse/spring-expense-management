package com.example.demo_mvn.presentation;


import com.example.demo_mvn.application.UserService;
import com.example.demo_mvn.application.dto.UserDTO;
import com.example.demo_mvn.presentation.rest.ApiResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        log.info("User register request recieved {}", userDTO);
        Optional<UserDTO> resp = userService.registerUser(userDTO);
        return resp.get();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO getUserByEmail(@PathVariable String email) {
        log.info("Get Userbyemail request {}", email);
        return userService.findUserByEmail(email);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        log.info("Update User object {}", userDTO);
        if (StringUtils.isBlank(userDTO.getEmail()))
            return null;
        return userService.updateUser(userDTO);
    }
}
