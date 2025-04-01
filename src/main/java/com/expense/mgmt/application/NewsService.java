package com.expense.mgmt.application;


import com.expense.mgmt.domain.model.dto.news.NewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NewsService {

    private final WebClient webClient;

    public NewsService(@Value("${newsapi.base-url}") String baseUrl, @Value("${newsapi.api-key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-Api-Key", apiKey)
                .build();
    }


    public Mono<NewsResponse> getTopHeadlines(String country) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("top-headlines")
                        .queryParam("country", country)
                        .build()
                )
                .retrieve()
                .bodyToMono(NewsResponse.class);
    }
}
