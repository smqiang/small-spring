package com.sima.springframework.beans.factory.support;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
            addSingleton(beanName, bean);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException(e.toString());
        }

        return bean;
    }
}
