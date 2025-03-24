package com.expense.mgmt.infrastructure.spring.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.User;
import com.expense.mgmt.infrastructure.repository.persistance.EntityMappers;
import com.expense.mgmt.domain.model.repository.UserRepository;
import com.expense.mgmt.infrastructure.util.JwtUtil;
import com.expense.mgmt.presentation.rest.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@Order(2)
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalResponseAdvise implements ResponseBodyAdvice<Object> {

    private final UserRepository userRepository;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body == null) {
            return ResponseEntity.notFound().build();
        }
        if (body instanceof ResponseEntity<?> || body instanceof ApiResponse<?>) {
            return body;
        } else {
            RequestMethod method = RequestMethod.resolve(request.getMethod());
            String requestURI = request.getURI().getPath();
            ApiResponse<Object> apiResponse = new ApiResponse<>(HttpStatus.OK, "Success", body);
            HttpStatus httpStatus = HttpStatus.OK;
            String token = null;
            if (method == RequestMethod.DELETE) {
                httpStatus = HttpStatus.ACCEPTED;
                apiResponse = new ApiResponse<>(httpStatus, "Success", body);
            }
            if (method == RequestMethod.POST && StringUtils.contains(requestURI, "create")) {
                httpStatus = HttpStatus.CREATED;
                apiResponse = new ApiResponse<>(httpStatus, "Created", body);
            }

            // set sample JWT Token
            SecurityContext context = SecurityContextHolder.getContext();
            if (context != null && context.getAuthentication() != null) {
                UserDetails userDetails = ((UserDetails) context.getAuthentication().getPrincipal());
                User user = userRepository.findByEmail(userDetails.getUsername()).get();
                token = JwtUtil.generateToken(user);
            }

            response.setStatusCode(httpStatus);
            return ResponseEntity.status(httpStatus).header("Authorization", token).body(apiResponse);
        }
    }
}
