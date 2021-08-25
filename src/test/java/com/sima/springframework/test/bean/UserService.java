package com.sima.springframework.test.bean;

/**
 * Created by qisima on 8/23/2021 10:44 PM
 */
public class UserService {
    private String userId;
    private UserDao userDao;

    public void sayHello(){
        System.out.println("hello, " + userDao.queryUserName(userId));
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
}
