package com.graduation.styleguide.controller;

import com.graduation.styleguide.config.auth.PrincipalDetails;
import com.graduation.styleguide.domain.Subscribe;
import com.graduation.styleguide.dto.UserInfoDto;
import com.graduation.styleguide.service.UserService;
import lombok.Builder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final UserService userService;

    private IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup") //회원 가입 폼으로 이동
    public String signup() {
        return "SignUp";
    }

    @GetMapping("/login") //로그인 화면으로 이동
    public String login() {
        return "Login";
    }

    @GetMapping("/")
    public String Home() {
        return "/layout/Home";
    }

    @GetMapping("/home")    // 로그인 후에 메인 화면
    public String HomeLogin(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){

        // 구독중인 스타일리스트 목록 반환
        List<String> subscribeList = userService.getSubscribeListInfo(principalDetails.getUserInfo().getUserID());
        model.addAttribute("subscribeList", subscribeList);

        return "/layout/HomeLogin";
    }
}
