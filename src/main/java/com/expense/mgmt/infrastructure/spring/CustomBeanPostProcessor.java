package com.expense.mgmt.infrastructure.spring;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// @Component // bean registered while application startup via constructor
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // set custom value or do DI works
        log.debug("Bean-postProcessBeforeInitialization {}", beanName);
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("Bean-postProcessAfterInitialization {}", beanName);
        return bean;
    }

}
