package com.sima.springframework.beans.factory.support;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            addSingleton(beanName, bean);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException(e.toString());
        }

        return bean;
    }
    
    private Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws InstantiationException, IllegalAccessException {
        Constructor constructorToUse = null;
        Class<?> clazz = beanDefinition.getBeanClass();
        for (Constructor ctor : clazz.getDeclaredConstructors()){
            if (null != args && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
