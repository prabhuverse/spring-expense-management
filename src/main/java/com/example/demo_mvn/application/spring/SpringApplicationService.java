package com.example.demo_mvn.application.spring;

import com.example.demo_mvn.application.dto.spring.ControllerInfo;
import com.example.demo_mvn.application.dto.spring.HandlerMethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SpringApplicationService {

	public Map<String, ControllerInfo> listMappingInfo(Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
		List<HandlerMethodInfo> methodInfos = handlerMethodMap.entrySet().stream()
				.map(entry -> HandlerMethodInfo.builder().name(entry.getValue().getBean().toString())
						.beanName(entry.getValue().getBeanType().getTypeName())
						.methodName(entry.getValue().getShortLogMessage()).build())
				.toList();
		Map<String, List<HandlerMethodInfo>> infos =
				methodInfos.stream().collect(Collectors.groupingBy(HandlerMethodInfo::name));
		Map<String, ControllerInfo> controllerInfo = infos.entrySet().stream().collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> ControllerInfo.builder().name(entry.getKey()).handlerMethodInfos(entry.getValue()).build()));
		return controllerInfo;
	}
}
