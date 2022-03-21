package com.shop.STYLE.GUIDE.controller;

import com.shop.STYLE.GUIDE.domain.tb_user_info;
import com.shop.STYLE.GUIDE.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SignController {

    private final UserService userService;

    @Autowired
    public SignController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/SignUp")
    public String SignUpForm(){
        return "SignUp";
    }

    @GetMapping("/SignIn")
    public String SignInForm(){
        return "SignIn";
    }

    @PostMapping("/SignUp")
    public String SignUp(tb_user_info user_info){
        log.info(user_info.toString());
        userService.join(user_info);
        log.info(user_info.toString());

        return "redirect:/Home";
    }

    @PostMapping("/SignIn")
    public String SignIn(){

        return "SignIn";
    }
}
