package com.expense.mgmt.presentation.user;


import com.expense.mgmt.application.UserService;
import com.expense.mgmt.domain.model.dto.User;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@Slf4j
// handles the API requests (acts as ports), interactions
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    // application service - orchestrates/delegates to the domain layer
    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.POST, path = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User registerUser(@RequestBody User user) {
        log.info("User register request recieved {}", user);
        return userService.registerUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserByEmail(@PathVariable String email) {
        log.info("Get Userbyemail request {}", email);
        return userService.findUserByEmail(email);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update", produces = {MediaType.APPLICATION_JSON_VALUE})
    public User updateUser(@RequestBody User userDTO) {
        log.info("Update User object {}", userDTO);
        if (StringUtils.isBlank(userDTO.getEmail()))
            return null;
        return userService.updateUser(userDTO);
    }
}
