package com.example.demo_mvn.infrastructure.spring.advice;

import com.example.demo_mvn.infrastructure.spring.annotation.MaskField;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import java.util.Iterator;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ResponseMaskingAdvice implements ResponseBodyAdvice<Object> {

	private final ObjectMapper objectMapper;


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
			JsonNode rootNode = objectMapper.valueToTree(body);
			// Apply field masking only to filed annotated with MaskField
			maskSensitiveFields(body, rootNode);
		} catch (IllegalAccessException e) {
			log.error("Error masking sensitive fields: {}", e.getMessage());
		}
		return body;
	}

	private void maskSensitiveFields(Object body, JsonNode jsonNode) throws IllegalAccessException {
		if (body == null || jsonNode == null || jsonNode.isNull())
			return;

		// Handle Lists and Sets (Iterate over DTOs)
		if (body instanceof Collection<?>) {
			Iterator<JsonNode> jsonElements = jsonNode.elements();
			for (Object item : (Collection<?>) body) {
				if (jsonElements.hasNext()) {
					maskSensitiveFields(item, jsonElements.next());
				}
			}
			return;
		}

		// Skip Java built-in types (e.g., LocalDate, LocalDateTime, etc.)
		if (isJavaBaseClass(body.getClass())) {
			return;
		}

		// Handle Maps (JSON Objects)
		// if (body instanceof Map<?, ?>) {
		// return;
		// }

		// Process DTOs (Only check fields that are present in the response JSON)
		for (Field field : body.getClass().getDeclaredFields()) {
			field.setAccessible(true);

			// If the field is NOT present in the response JSON, skip it
			if (!jsonNode.has(field.getName()))
				continue;

			Object fieldValue = field.get(body);

			// If field has @MaskField annotation, replace value
			if (field.isAnnotationPresent(MaskField.class)) {
				MaskField annotation = field.getAnnotation(MaskField.class);
				field.set(body, annotation.maskWith());
			}
			// If field is a nested object, recursively mask
			else if (fieldValue != null && !isPrimitiveOrWrapper(fieldValue.getClass())) {
				maskSensitiveFields(fieldValue, jsonNode.get(field.getName()));
			}
		}
	}

	// Skip reflection on Java system classes (Prevents `InaccessibleObjectException`).
	private boolean isJavaBaseClass(Class<?> clazz) {
		return clazz.getName().startsWith("java.") || clazz.getName().startsWith("javax.") || clazz.isPrimitive()
				|| clazz == String.class || Number.class.isAssignableFrom(clazz)
				|| Boolean.class.isAssignableFrom(clazz);
	}

	private boolean isPrimitiveOrWrapper(Class<?> clazz) {
		return clazz.isPrimitive() || clazz == String.class || Number.class.isAssignableFrom(clazz)
				|| Boolean.class.isAssignableFrom(clazz);
	}

	private boolean isApplicationDTO(Object obj) {
		if (obj == null)
			return false;
		Class<?> clazz = obj.getClass();
		return clazz.getName().startsWith("com.example.demo_mvn");
	}
}
