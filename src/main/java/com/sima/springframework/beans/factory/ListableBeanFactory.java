package com.sima.springframework.beans.factory;

import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.factory.BeanFactory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /*
    * 返回注册表中所有bean名称
    * */
    String[] getBeanDefinitionNames();
}
