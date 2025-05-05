package com.expense.mgmt.domain.model.dto.kafka;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
public record StreamingObjectInfo(StreamingType type, String topic, Object message) {


    public enum StreamingType {

        KAFKA,
        ELASTICSEARCH;

    }
}
