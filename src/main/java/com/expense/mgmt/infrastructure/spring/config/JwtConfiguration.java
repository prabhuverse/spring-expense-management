package com.expense.mgmt.infrastructure.spring.config;

import lombok.RequiredArgsConstructor;

import com.expense.mgmt.infrastructure.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtConfiguration {

    @Value("${jwt.secret.key:secret-key}")
    private String key;

    @Value("${jwt.expirationinsecs:60}")
    private int expirationInSecs;


    private final ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        JwtUtil.init(objectMapper);
        JwtUtil.setSecretKey(key);
        JwtUtil.setExpirationInSecs(expirationInSecs);
    }

}
