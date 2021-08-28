package com.sima.springframework.beans.factory.config;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
