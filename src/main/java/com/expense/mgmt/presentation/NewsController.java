package com.expense.mgmt.presentation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.application.NewsService;
import com.expense.mgmt.domain.model.dto.news.NewsResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NewsController {

    private final NewsService newsService;

    @RequestMapping(value = "/top-headlines", method = RequestMethod.GET, consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public Mono<NewsResponse> getTopHeadlines(@RequestParam(defaultValue = "us") String country) {
        Mono<NewsResponse> newsResponseMono = newsService.getTopHeadlines(country);

        newsResponseMono.map(news -> {
            log.info("News Count {}", news.getArticles().size());
            return news;
        }).doOnSuccess( newsResponse -> {
            log.info("Succesfully completed {} ", newsResponse.getTotalResults());
        }).doOnError((err) -> {
            log.error("Error Occured while fetching news ", err);
        }).log();

        return newsResponseMono;
    }
}
