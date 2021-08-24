package com.sima.springframework.beans.factory.support;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.BeanFactory;
import com.sima.springframework.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeanException {
        Object bean = getSingleton(beanName);
        if (bean != null){
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeanException;
}
