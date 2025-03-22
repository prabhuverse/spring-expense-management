package com.example.demo_mvn.presentation.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@RequiredArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private final HttpStatus status;

	private final String message;

	private final T data;

}
