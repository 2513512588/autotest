package com.runnersoftware.auto_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AutoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoTestApplication.class, args);
    }

}
