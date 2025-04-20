package com.expense.mgmt.infrastructure.spring;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

// @Component // bean registered while application startup via constructor
@Slf4j
public class CustomScopeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private final static String DEMO_MVN_PREFIX = "com.expense.mgmt";

    private final static String EXCLUDE_SPRING_APP_PACKAGE = "com.expense.mgmt.infrastructure.spring";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            String beanClassName = beanFactory.getBeanDefinition(beanName).getBeanClassName();
            if (StringUtils.isNotBlank(beanClassName) && StringUtils.startsWith(beanClassName, DEMO_MVN_PREFIX)
                && !StringUtils.startsWith(beanClassName, EXCLUDE_SPRING_APP_PACKAGE)) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                log.debug("Custom Scope BeanPostFactoryProcessor {}", beanName);
                beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
                // beanDefinition.setLazyInit(true);
                beanDefinition.setAutowireCandidate(true);
            }
        }
    }
}
