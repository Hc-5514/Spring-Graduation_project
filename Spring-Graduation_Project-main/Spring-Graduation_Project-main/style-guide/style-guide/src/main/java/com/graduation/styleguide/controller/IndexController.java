package com.graduation.styleguide.controller;

import com.graduation.styleguide.service.UserService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final UserService userService;

    private IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup") //회원 가입 폼으로 이동
    public String signup() {
        return "signup";
    }

    @GetMapping("/login") //로그인 화면으로 이동
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String Home() {
        return "Home";
    }

    @GetMapping("/test")
    public String test() {
        return "testSubscribe";
    }
}
