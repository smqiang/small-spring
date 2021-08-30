package com.sima.springframework.context;

import com.sima.springframework.beans.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext {
    void refresh() throws BeanException;
    void registerShutdownHook();
    void close();
}
