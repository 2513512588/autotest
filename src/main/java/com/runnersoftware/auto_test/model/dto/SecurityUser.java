package com.runnersoftware.auto_test.model.dto;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;

public class SecurityUser extends User {

    private final com.runnersoftware.auto_test.model.User user;

    public SecurityUser(com.runnersoftware.auto_test.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public com.runnersoftware.auto_test.model.User getUser() {
        return user;
    }
}
