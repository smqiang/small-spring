package com.sima.springframework.beans.factory.config;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
    Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName) throws BeanException;
    Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName) throws BeanException;
}
