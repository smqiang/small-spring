package com.sima.springframework.context.support;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.sima.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.sima.springframework.beans.factory.config.BeanPostProcessor;
import com.sima.springframework.context.ConfigurableApplicationContext;
import com.sima.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    protected abstract ConfigurableListableBeanFactory getBeanFactory();
    protected abstract void refreshBeanFactory();

    @Override
    public void refresh() throws BeanException {
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        invokeBeanFactoryPostProcessors(beanFactory);
        registerBeanPostProcessors(beanFactory);
        beanFactory.preInstantiateSingletons();
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> processorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        processorMap.forEach((beanName, processor) -> {
            processor.postProcessBeanFactory(beanFactory);
        });
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> processorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor processor : processorMap.values()){
            beanFactory.addBeanPostProcessor(processor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) throws BeanException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeanException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
