package com.sima.springframework.beans.factory;

import com.sima.springframework.beans.BeanException;

/**
 * Created by qisima on 8/30/2021 9:15 PM
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
