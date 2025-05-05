package com.expense.mgmt.infrastructure.spring.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.application.spring.ElasticsearchService;
import com.expense.mgmt.domain.model.dto.LogDocument;
import com.expense.mgmt.domain.model.dto.kafka.StreamingObjectInfo;
import com.expense.mgmt.infrastructure.spring.config.kafka.KafkaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(3)
@RestControllerAdvice
public class GlobalLoggingAdvice implements ResponseBodyAdvice<Object> {

    private final ElasticsearchService elasticsearchService;

    private final KafkaService kafkaService;

    private final ObjectMapper objectMapper;
    //private final Gson gson = new Gson();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, org.springframework.http.server.ServerHttpResponse response) {
        String path = request.getURI().getPath();
        try {
            LogDocument doc = LogDocument.builder()
                    .path(path)
                    .message(objectMapper.writeValueAsString(body))
                    .build();
            elasticsearchService.logToElastic(doc, "loginfo").subscribe();
            kafkaService.send(StreamingObjectInfo.builder().type(StreamingObjectInfo.StreamingType.KAFKA).topic("log").message(doc).build());
        } catch (Exception e) {
            log.error("Error occured while logging to elasticsearch {} cause", path, e);
        }
        return body;
    }
}
