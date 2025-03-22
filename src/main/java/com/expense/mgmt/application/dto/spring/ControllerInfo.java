package com.expense.mgmt.application.dto.spring;

import lombok.Builder;

import java.util.List;


@Builder
public record ControllerInfo(String name, List<HandlerMethodInfo> handlerMethodInfos) {
}
