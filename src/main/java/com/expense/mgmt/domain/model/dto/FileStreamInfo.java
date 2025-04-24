package com.expense.mgmt.domain.model.dto;

import lombok.Builder;

import software.amazon.awssdk.core.ResponseBytes;

import java.io.InputStream;

@Builder
public record FileStreamInfo(String path, String contentType, Integer contentLength, byte[] bytes) {
}
