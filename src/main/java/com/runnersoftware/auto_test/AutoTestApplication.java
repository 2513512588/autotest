package com.runnersoftware.auto_test;

import com.runnersoftware.auto_test.service.ExecuteCmdService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class AutoTestApplication implements InitializingBean {

    @Autowired
    private ExecuteCmdService executeCmdService;
    @Value("${tomcat.path}")
    private String tomcatPath;

    public static void main(String[] args) {
        SpringApplication.run(AutoTestApplication.class, args);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        executeCmdService.execCmd("cmd /c start startup.bat", new File(tomcatPath));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
