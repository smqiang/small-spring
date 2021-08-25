package com.sima.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> map = new HashMap<>();

    static{
        map.put("001", "zz");
        map.put("002", "cici");
        map.put("003", "bb");
    }

    public String queryUserName(String uid){
        return map.get(uid);
    }
}
