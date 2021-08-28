package com.sima.springframework.context.support;

import com.sima.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sima.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configurations = getConfigurations();
        if (null != configurations){
            reader.loadBeanDefinitions(configurations);
        }
    }

    protected abstract String[] getConfigurations();
}
