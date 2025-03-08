package com.example.demo_mvn.application.dto.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;

@Builder
@JsonIgnoreProperties(value = {"name"})
public record HandlerMethodInfo(String name, String beanName, String methodName) {

}
