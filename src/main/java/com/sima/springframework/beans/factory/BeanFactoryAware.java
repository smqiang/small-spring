package com.sima.springframework.beans.factory;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.Aware;

/**
 * Created by qisima on 8/30/2021 9:12 PM
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeanException;
}
