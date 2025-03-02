package com.example.demo_mvn.infrastructure.spring.interceptors;

import com.example.demo_mvn.infrastructure.spring.annotation.MaskField;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Field;
import java.util.Collection;

@Slf4j
@ControllerAdvice
public class ResponseMaskingAdvice implements ResponseBodyAdvice<Object> {

	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body == null) {
			return null;
		}

		try {
			// Apply field masking only to DTOs in "com.example.demo_mvn"
			if (isApplicationDTO(body)) {
				maskSensitiveFields(body);
			}
		} catch (IllegalAccessException e) {
			log.error("Error masking sensitive fields: {}", e.getMessage());
		}

		return body;
	}

	private void maskSensitiveFields(Object body) throws IllegalAccessException {
		if (body == null)
			return;

		// Handle Lists and Sets (Iterate over DTOs)
		if (body instanceof Collection<?>) {
			for (Object item : (Collection<?>) body) {
				if (isApplicationDTO(item)) {
					maskSensitiveFields(item);
				}
			}
			return;
		}

		// Process DTOs (Plain Java Objects)
		for (Field field : body.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object fieldValue = field.get(body);

			// If field has @MaskField annotation, replace value
			if (field.isAnnotationPresent(MaskField.class)) {
				MaskField annotation = field.getAnnotation(MaskField.class);
				String maskValue = annotation.maskWith();
				field.set(body, maskValue);
			}
			// If field is a nested object, recursively mask
			else if (fieldValue != null && isApplicationDTO(fieldValue)) {
				maskSensitiveFields(fieldValue);
			}
		}
	}

	private boolean isApplicationDTO(Object obj) {
		if (obj == null)
			return false;
		Class<?> clazz = obj.getClass();
		return clazz.getName().startsWith("com.example.demo_mvn");
	}
}
