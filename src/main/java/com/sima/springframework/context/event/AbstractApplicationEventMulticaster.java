package com.sima.springframework.context.event;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.BeanFactory;
import com.sima.springframework.beans.factory.BeanFactoryAware;
import com.sima.springframework.context.ApplicationEvent;
import com.sima.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.List;

public class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private final List<ApplicationListener> listeners = new ArrayList<>();
    private BeanFactory beanFactory;
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = beanFactory;
    }
}
