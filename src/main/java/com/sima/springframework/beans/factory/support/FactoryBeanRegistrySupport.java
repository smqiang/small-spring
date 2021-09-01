package com.sima.springframework.beans.factory.support;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName) {
        Object obj = this.factoryBeanObjectCache.get(beanName);
        return obj == NULL_OBJECT ? null : obj;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName){
        if (factory.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (null == object) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, object == null ? NULL_OBJECT : object);
            }
            return object == null ? NULL_OBJECT : object;
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            throw new BeanException("FactoryBean throw exception on object[" + beanName + "] creation.", e);
        }
    }
}
