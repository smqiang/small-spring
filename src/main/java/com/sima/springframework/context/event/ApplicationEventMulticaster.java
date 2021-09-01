package com.sima.springframework.context.event;

import com.sima.springframework.context.ApplicationEvent;
import com.sima.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);
    void removeApplicationListener(ApplicationListener<?> listener);
    void multicastEvent(ApplicationEvent event);
}
