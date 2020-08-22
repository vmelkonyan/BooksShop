package com.example.demo.utils;

import com.example.demo.domian.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {
    public static void updateAuthentication(User user) {

        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getId().equals(sessionUser.getId())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
