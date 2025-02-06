package com.example.demo_mvn.presentation.rest;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private HttpStatus status;

	private String message;

	private T data;

}
