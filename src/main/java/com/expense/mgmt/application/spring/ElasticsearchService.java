package com.expense.mgmt.application.spring;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.LogDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.google.gson.Gson;

@Slf4j
@RequiredArgsConstructor
@Service
public class ElasticsearchService {

    @Qualifier("esClient")
    private final WebClient webClient;

    //private final Gson gson = new Gson();
    private final ObjectMapper mapper;

    @SneakyThrows
    public Mono<Void> logToElastic(LogDocument logDocument, String index) {
        return webClient.post()
                .uri("{index}/_doc", index)
                .bodyValue(mapper.writeValueAsString(logDocument))
                .retrieve()
                .bodyToMono(String.class).then();
    }
}
