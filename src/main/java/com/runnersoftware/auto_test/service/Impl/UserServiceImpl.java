package com.runnersoftware.auto_test.service.Impl;

import com.runnersoftware.auto_test.service.IUserService;
import com.runnersoftware.auto_test.utils.ManagerProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements IUserService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!ManagerProperties.USERNAME.equals(s)){
            throw new UsernameNotFoundException("用户不存在!");
        }
        return new User(s, ManagerProperties.PASSWORD, Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
