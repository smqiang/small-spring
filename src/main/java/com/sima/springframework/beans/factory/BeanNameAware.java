package com.sima.springframework.beans.factory;

/**
 * Created by qisima on 8/30/2021 9:14 PM
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
