package com.sima.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by qisima on 8/23/2021 10:37 PM
 */
public class BeanFactory {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName){
        return beanDefinitionMap.get(beanName).getBean();
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
