package com.sima.springframework.beans.factory;

import com.sima.springframework.beans.BeanException;

/**
 * Created by qisima on 8/23/2021 10:37 PM
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeanException;

    Object getBean(String beanName, Object... args) throws BeanException;

}
