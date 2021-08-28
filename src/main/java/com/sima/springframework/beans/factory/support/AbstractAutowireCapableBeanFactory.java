package com.sima.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.PropertyValue;
import com.sima.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.config.BeanPostProcessor;
import com.sima.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
            bean = initializeBean(beanName, bean, beanDefinition);
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

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        wrappedBean = applyBeanPostProcessorAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    // TODO
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition definition){

    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object bean, String beanName) throws BeanException {
        Object result = bean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object wrapped = processor.postProcessBeforeInitialization(result, beanName);
            if (wrapped == null){
                return result;
            }
            result = wrapped;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName) throws BeanException {
        Object result = bean;
        for (BeanPostProcessor processor : getBeanPostProcessors()){
            Object wrapped = processor.postProcessAfterInitialization(result, beanName);
            if (wrapped == null){
                return result;
            }
            result = wrapped;
        }
        return result;
    }
}
