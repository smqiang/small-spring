package com.sima.springframework.beans.factory.support;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {
    @Override
    public Object instantiate(BeanDefinition definition, String beanName, Constructor ctor, Object[] args) throws BeanException {
        Class clazz = definition.getBeanClass();
        Object obj;
        try {
            if (null != ctor){
                obj = clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else{
                obj = clazz.getDeclaredConstructor().newInstance();
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanException("Fail to instantiate [" + clazz.getName() + "]", e);
        }

        return obj;
    }
}
