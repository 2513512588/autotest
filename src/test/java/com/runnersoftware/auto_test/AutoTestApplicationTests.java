package com.runnersoftware.auto_test;

import com.alibaba.fastjson.JSONArray;
import com.runnersoftware.auto_test.mapper.UserMapper;
import com.runnersoftware.auto_test.model.User;
import com.runnersoftware.auto_test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AutoTestApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println("passwordEncoder = " + passwordEncoder.encode("123"));
    }

    @Test
    void testFindUsername(){
        System.out.println("user => " + userMapper.findByUsername("root"));
    }

    @Test
    void generateAdmin(){
        User user = new User();
        user.setUsername("root");
        user.setPassword("123");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(5);
        user.setRole(jsonArray.toJSONString());
        userService.insert(user);
    }

}
