package com.sima.springframework.test.bean;

import com.sima.springframework.beans.factory.DisposableBean;
import com.sima.springframework.beans.factory.InitializingBean;

/**
 * Created by qisima on 8/23/2021 10:44 PM
 */
public class UserService implements InitializingBean, DisposableBean {
    private String userId;
    private String company;
    private String location;
    private UserDao userDao;

    public void sayHello(){
        System.out.println("hello, " + userDao.queryUserName(userId) + " from " + getCompany() + " in " +getLocation() );
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("calling destroy()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("calling afterPropertiesSet()");
    }
}
