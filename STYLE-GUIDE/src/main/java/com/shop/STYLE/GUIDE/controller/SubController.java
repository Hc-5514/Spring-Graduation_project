package com.shop.STYLE.GUIDE.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SubController {

    @GetMapping("/portfolio-details")
    public String PortfolioForm(){
        return "Portfolio";
    }
}
