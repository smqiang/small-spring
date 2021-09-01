package com.sima.springframework.context;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
