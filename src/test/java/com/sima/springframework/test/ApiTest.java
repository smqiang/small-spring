package com.sima.springframework.test;

import com.sima.springframework.beans.PropertyValue;
import com.sima.springframework.beans.PropertyValues;
import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.BeanFactory;
import com.sima.springframework.beans.factory.config.BeanReference;
import com.sima.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sima.springframework.test.bean.UserDao;
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
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues pvs = new PropertyValues();
        pvs.addProperty(new PropertyValue("userId", "002"));
        pvs.addProperty(new PropertyValue("userDao", new BeanReference("userDao")));

        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, pvs));

        // get bean
        ((UserService)beanFactory.getBean("userService")).sayHello();
    }
}
