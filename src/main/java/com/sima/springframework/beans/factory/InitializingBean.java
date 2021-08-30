package com.sima.springframework.beans.factory;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
