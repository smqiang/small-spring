package com.sima.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> map = new HashMap<>();

    public void initDataMethod() {
        System.out.println("calling initDataMethod()");
        map.put("001", "zz");
        map.put("002", "cici");
        map.put("003", "bb");
    }

    public String queryUserName(String uid){
        return map.get(uid);
    }

    public void destroyDataMethod() {
        System.out.println("calling destroyDataMethod()");
        map.clear();
    }
}
