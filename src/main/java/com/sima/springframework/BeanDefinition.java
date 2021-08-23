package com.sima.springframework;

/**
 * Created by qisima on 8/23/2021 10:34 PM
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
