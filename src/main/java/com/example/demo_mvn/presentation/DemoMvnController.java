package com.example.demo_mvn.presentation;


import com.example.demo_mvn.presentation.rest.ApiResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Slf4j
@RestController
public class DemoMvnController {


    @GetMapping(value = "/status", produces = {MediaType.APPLICATION_JSON_VALUE})
    // produces = {MediaType.APPLICATION_JSON_VALUE}
    public ResponseEntity<ApiResponse<String>> healthStatus() {
        log.info("Request reached health controller {}", LocalDateTime.now());
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK, "success", "OK");
        return ResponseEntity.ok(response);
    }
}
