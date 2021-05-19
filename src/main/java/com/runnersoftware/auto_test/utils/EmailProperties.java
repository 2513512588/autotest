package com.runnersoftware.auto_test.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailProperties implements InitializingBean {

    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;
    @Value("${email.sender}")
    private String sender;

    public static String USERNAME;
    public static String PASSWORD;
    public static String SENDER;


    @Override
    public void afterPropertiesSet() throws Exception {
        USERNAME = username;
        PASSWORD = password;
        SENDER = sender;
    }
}
