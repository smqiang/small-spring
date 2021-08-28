package com.sima.springframework.test.common;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.PropertyValue;
import com.sima.springframework.beans.PropertyValues;
import com.sima.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues pvs = beanDefinition.getPropertyValues();
        pvs.addProperty(new PropertyValue("location", "suzhou -> shanghai"));
    }
}
