package com.example.demo.controller;


import com.example.demo.constatns.KeyConstants;
import com.example.demo.domian.User;
import com.example.demo.domian.UserRole;
import com.example.demo.exception.AppException;
import com.example.demo.service.UserService;
import com.example.demo.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(KeyConstants.USER_KEY)
@PreAuthorize("hasAnyAuthority('MANAGER')")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    @GetMapping
    public String getUserList(Model model) {
        model.addAttribute("userRoles", UserRole.values());
        model.addAttribute("users", userService.findAll());
        return KeyConstants.BOOK_LIST;
    }

    @PostMapping()
    public String updateUser(@RequestParam String username,
                             @RequestParam Map<String, String> form,
                             @RequestParam("userId") User user) {
        user.setUsername(username);


        AppUtils.updateAuthentication(user);

        Set<String> roles = Arrays.stream(UserRole.values()).map(UserRole::name).collect(Collectors.toSet());

        if (form.get("userRol") != null) {
            user.getUserRoles().clear();
            user.getUserRoles().add(UserRole.valueOf(form.get("userRol")));
        }

        userService.save(user);
        log.info("user correct updated is {} ", user.getUsername());
        return KeyConstants.REDIRECT_KEY + KeyConstants.USER_KEY;
    }

    @ExceptionHandler(AppException.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "User not Found");
        mav.setViewName(KeyConstants.ERROR_VIEW_KEY);
        return mav;
    }
}
