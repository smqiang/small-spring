package com.sima.springframework.test;

import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.BeanFactory;
import com.sima.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sima.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * Created by qisima on 8/23/2021 10:43 PM
 */
public class ApiTest {
    @Test
    public void test_BeanFactory(){
        // init bean factory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // register bean definition
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));

        // get bean
        ((UserService)beanFactory.getBean("userService", "zz")).sayHello();
    }
}
