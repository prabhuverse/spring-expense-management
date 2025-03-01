package com.example.demo_mvn.infrastructure.spring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@Order(1)
public class LoggingFilter extends OncePerRequestFilter {

	/**
	 * before reaching to servletDispatcher, this filter will be logging the execution time each request <br>
	 * Lifecylce : client ->> filter ->> serveltDispatcher ->> handlerMapping ->> controller ->> response ->> filter
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		String method = request.getMethod() + " " + request.getRequestURI();
		// log.info("LoggingFilter Incoming call {}", method);
		filterChain.doFilter(request, response);
		long duration = System.currentTimeMillis() - startTime;
		log.info("LoggingFilter Outgoing call {} response took {}", method, duration);
	}
}
