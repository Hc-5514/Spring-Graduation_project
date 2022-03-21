package com.shop.STYLE.GUIDE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String IndexForm(){
        return "Home";
    }

    // 미작동
    @GetMapping("/post/story")
    public String MyPageForm(){
        return "MyPage";
    }

    @GetMapping("/sub")
    public String SubForm(){
        return "SubIndex1";
    }
}
