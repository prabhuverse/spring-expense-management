package com.example.demo_mvn.infrastructure.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

// @Component // bean registered while application startup via constructor
@Slf4j
public class CustomScopeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private final static String DEMO_MVN_PREFIX = "com.example.demo_mvn";

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		for (String beanName : beanFactory.getBeanDefinitionNames()) {
			String beanClassName = beanFactory.getBeanDefinition(beanName).getBeanClassName();
			if (StringUtils.isNotBlank(beanClassName) && StringUtils.startsWith(beanClassName, DEMO_MVN_PREFIX)) {
				BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
				log.info("Custom Scope BeanPostFactoryProcessor {}", beanName);
				beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
				// beanDefinition.setLazyInit(true);
				beanDefinition.setAutowireCandidate(true);
			}
		}
	}
}
