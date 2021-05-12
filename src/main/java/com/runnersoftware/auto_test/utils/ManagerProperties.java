package com.runnersoftware.auto_test.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ManagerProperties implements InitializingBean {

    @Value("${manager.username}")
    private String username;
    @Value("${manager.password}")
    private String password;

    public static String USERNAME;
    public static String PASSWORD;

    @Override
    public void afterPropertiesSet() throws Exception {
        USERNAME = username;
        PASSWORD = password;
    }
}
