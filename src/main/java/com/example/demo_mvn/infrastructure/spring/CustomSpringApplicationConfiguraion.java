package com.example.demo_mvn.infrastructure.spring;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSpringApplicationConfiguraion {

	@Bean
	public BeanFactoryPostProcessor customScopeBeanPostProcessorFactory() {
		return new CustomScopeBeanFactoryPostProcessor();
	}

	@Bean
	public BeanPostProcessor customBeanPostProcessor() {
		return new CustomBeanPostProcessor();
	}
}
