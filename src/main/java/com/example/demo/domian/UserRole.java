package com.example.demo.domian;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    EMPLOYEE,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
