package com.sima.springframework.context.support;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configurations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configuration) {
        this(new String[]{configuration});
    }

    public ClassPathXmlApplicationContext(String[] configurations) {
        this.configurations = configurations;
        refresh();
    }

    @Override
    protected String[] getConfigurations() {
        return this.configurations;
    }
}
