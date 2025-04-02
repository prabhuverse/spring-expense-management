package com.expense.mgmt.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@Data
public class LogDocument {

    private String path;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private Integer httpcode;

}
