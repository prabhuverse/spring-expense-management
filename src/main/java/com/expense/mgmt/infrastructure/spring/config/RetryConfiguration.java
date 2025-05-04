package com.expense.mgmt.infrastructure.spring.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Slf4j
@Configuration
public class RetryConfiguration {

    @Bean
    public RetryTemplate retryTemplate() {
        int maxAttempts = 2;
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .customPolicy(new SimpleRetryPolicy(maxAttempts))
                .customBackoff(new FixedBackOffPolicy())
                .build();

        // register logging listener
        retryTemplate.registerListener(new RetryLogListener());

        return retryTemplate;
    }
}
