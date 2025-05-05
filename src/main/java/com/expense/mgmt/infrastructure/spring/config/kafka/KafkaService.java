package com.expense.mgmt.infrastructure.spring.config.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.domain.model.dto.kafka.StreamingObjectInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaService {

    private final KafkaTemplate<String, String> template;

    private final ObjectMapper objectMapper;

    public void send(StreamingObjectInfo info) {
        try {
            if (info.message() != null) {
                String message = objectMapper.writeValueAsString(info.message());
                CompletableFuture<SendResult<String, String>> future = template.send(info.topic(), message);
                SendResult<String, String> result = future.get(60, TimeUnit.SECONDS);
                log.debug("Message sent {}", result.getRecordMetadata());
            }
        } catch (Exception e) {
            log.error("unable to send message to {} cause ", info.topic(), e);
        }
    }
}
