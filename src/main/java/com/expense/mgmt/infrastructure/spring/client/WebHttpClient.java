package com.expense.mgmt.infrastructure.spring.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebHttpClient {

    @Bean(name = "esClient")
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:9200")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
