package com.sima.springframework.beans.factory.support;

import com.sima.springframework.beans.factory.DisposableBean;
import com.sima.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected final Object NULL_OBJECT = new Object();
    private Map<String, Object> singletonMap = new HashMap<>();
    private Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        singletonMap.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length-1; i >= 0 ; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean bean = disposableBeanMap.remove(beanName);
            try {
                bean.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
