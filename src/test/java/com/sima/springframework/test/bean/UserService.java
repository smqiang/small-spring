package com.sima.springframework.test.bean;

/**
 * Created by qisima on 8/23/2021 10:44 PM
 */
public class UserService {
    private String userName;

    public UserService(String userName) {
        this.userName = userName;
    }

    public void sayHello(){
        System.out.println("hello, " + this.userName);
    }
}
