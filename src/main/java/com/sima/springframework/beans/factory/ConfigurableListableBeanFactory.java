package com.sima.springframework.beans.factory;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;
}
