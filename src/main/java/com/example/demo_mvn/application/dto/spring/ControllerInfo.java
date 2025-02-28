package com.example.demo_mvn.application.dto.spring;

import lombok.Builder;

import java.util.List;


@Builder
public record ControllerInfo(String name, List<HandlerMethodInfo> handlerMethodInfos) {
}
