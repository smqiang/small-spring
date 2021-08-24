package com.sima.springframework.beans.factory.config;

/**
 * Created by qisima on 8/23/2021 10:34 PM
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class bean) {
        this.beanClass = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
