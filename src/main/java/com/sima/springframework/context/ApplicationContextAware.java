package com.sima.springframework.context;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeanException;
}
