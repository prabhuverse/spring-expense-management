package com.expense.mgmt.infrastructure.spring.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("preHandle Request {} and URI {} and status {}", request.getMethod(), request.getRequestURI(),
                response.getStatus());
        return true; // continue request processing
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) {
        log.debug("postHandle Request {} and URI {} and status {}", request.getMethod(), request.getRequestURI(),
                response.getStatus());
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) {
        log.debug("afterCompletion Request {} and URI {} and status {}", request.getMethod(), request.getRequestURI(),
                response.getStatus());
    }
}
