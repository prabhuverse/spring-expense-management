package com.example.demo_mvn.infrastructure.spring.security;

import com.example.demo_mvn.application.spring.CustomUserDetailsService;
import com.example.demo_mvn.domain.model.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	@SneakyThrows
	public SecurityFilterChain securityFilterChain(HttpSecurity security) {
		security.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
			auth.requestMatchers("/public/**").permitAll().anyRequest().authenticated();
		}).sessionManagement(sess -> {
			sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}).httpBasic(Customizer.withDefaults());
		return security.build();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		return new CustomUserDetailsService(userRepository, passwordEncoder);
	}

	@Bean
	public AuthenticationConfiguration authenticationConfiguration() {
		return new AuthenticationConfiguration();
	}

}
