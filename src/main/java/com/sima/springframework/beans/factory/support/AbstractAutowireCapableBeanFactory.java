package com.sima.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.PropertyValue;
import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
            addSingleton(beanName, bean);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException(e.toString());
        }

        return bean;
    }
    
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws InstantiationException, IllegalAccessException {
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

    /*
    * bean属性填充
    * */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        for (PropertyValue pv : beanDefinition.getPropertyValues().getPropertyValues()){
            String name = pv.getName();
            Object value = pv.getValue();

            if (value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }

            BeanUtil.setFieldValue(bean, name, value);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
