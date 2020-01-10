package com.troy.user.service.foreign;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author
 * @date 2018-12-19 下午4:14:21
 * @copyright
 */
@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private static final String NAME_FEIGN_CONTEXT = "feignContext";
    private static final String NAME_EUREKA_AUTO_SERVICE_REGISTRATION = "eurekaAutoServiceRegistration";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, NAME_FEIGN_CONTEXT, NAME_EUREKA_AUTO_SERVICE_REGISTRATION)) {
            BeanDefinition bd = beanFactory.getBeanDefinition(NAME_FEIGN_CONTEXT);
            bd.setDependsOn(NAME_EUREKA_AUTO_SERVICE_REGISTRATION);
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {
        return Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }
}