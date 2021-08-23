package com.sima.springframework.test;

import com.sima.springframework.BeanDefinition;
import com.sima.springframework.BeanFactory;
import com.sima.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * Created by qisima on 8/23/2021 10:43 PM
 */
public class ApiTest {
    @Test
    public void test_BeanFactory(){
        // init bean factory
        BeanFactory beanFactory = new BeanFactory();

        // register bean definition
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(new UserService()));

        // get bean
        ((UserService)beanFactory.getBean("userService")).sayHello();
    }
}
