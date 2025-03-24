package com.expense.mgmt.domain.model.dto.spring;

import lombok.Builder;

import java.util.List;


@Builder
public record ControllerInfo(String name, List<HandlerMethodInfo> handlerMethodInfos) {
}
