package com.sima.springframework.test.common;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.config.BeanPostProcessor;
import com.sima.springframework.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if ("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setCompany("[" + userService.getCompany() + "] -> [ali]");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
