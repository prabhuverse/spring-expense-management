package com.example.demo_mvn.infrastructure.spring.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Order(2)
public class MDCContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetails userDetails = ((UserDetails) context.getAuthentication().getPrincipal());
        WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) context.getAuthentication().getDetails();
        MDC.put("email", userDetails.getUsername());
        MDC.put("ip", authenticationDetails.getRemoteAddress());
        filterChain.doFilter(request, response);
        MDC.clear();
    }
}
