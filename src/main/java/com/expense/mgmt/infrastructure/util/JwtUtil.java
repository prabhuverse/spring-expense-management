package com.expense.mgmt.infrastructure.util;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import com.expense.mgmt.application.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class JwtUtil {

    @Setter
    private String secretKey;

    @Setter
    private int expirationInSecs;

    private ObjectMapper mapper;

    public void init(ObjectMapper objectMapper) {
        mapper = objectMapper;
    }

    @SneakyThrows
    public String generateToken(final UserDTO userDTO) {
        userDTO.setPassword(null);
        LocalDateTime currentTime = LocalDateTime.now();
        return Jwts.builder()
                //header
                .signWith(SignatureAlgorithm.HS256, secretKey)
                //payload
                .setSubject(mapper.writeValueAsString(userDTO))
                .setIssuedAt(date(currentTime))
                .setExpiration(date(currentTime.plusSeconds(expirationInSecs)))
                .compact();
    }

    private Date date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
