package com.expense.mgmt.presentation;

import com.expense.mgmt.domain.model.dto.spring.ControllerInfo;
import com.expense.mgmt.application.spring.SpringApplicationService;
import com.expense.mgmt.presentation.rest.ApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping(value = "/api/v1/app")
@RestController
public class SpringApplicationController {

    // @Qualifier(value = "requestMappingHandlerMapping")
    private final RequestMappingHandlerMapping handlerMapping;

    // @Qualifier(value = "controllerEndpointHandlerMapping")
    private final RequestMappingHandlerMapping controllerHandlerMapping;

    private final SpringApplicationService applicationService;

    public SpringApplicationController(
            @Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping handlerMapping,
            @Qualifier("controllerEndpointHandlerMapping") RequestMappingHandlerMapping controllerHandlerMapping,
            SpringApplicationService applicationService) {
        this.handlerMapping = handlerMapping;
        this.controllerHandlerMapping = controllerHandlerMapping;
        this.applicationService = applicationService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/mapping/{type}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<Map<String, ControllerInfo>>> listHandlerMappingMethods(
            @PathVariable(required = false) String type) {
        Map<RequestMappingInfo, HandlerMethod> detail = new LinkedHashMap<>();
        if (StringUtils.isNotBlank(type) && StringUtils.equals(type, "controller")) {
            detail = controllerHandlerMapping.getHandlerMethods();
        } else {
            detail = handlerMapping.getHandlerMethods();
        }
        Map<String, ControllerInfo> mappingInfo = applicationService.listMappingInfo(detail);
        ApiResponse<Map<String, ControllerInfo>> apiResponse = new ApiResponse<>(HttpStatus.OK, "success", mappingInfo);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
