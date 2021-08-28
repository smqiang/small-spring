package com.sima.springframework.util;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        cl = Thread.currentThread().getContextClassLoader();
        if (null == cl) {
            cl = ClassUtils.class.getClassLoader();
        }

        return cl;
    }
}
