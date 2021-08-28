package com.sima.springframework.beans.factory.config;

import com.sima.springframework.beans.BeanException;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
