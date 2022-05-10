package com.graduation.styleguide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("Home");
        registry.addViewController("/test2").setViewName("test");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/profile").setViewName("MyPage");
        registry.addViewController("/admin").setViewName("adminpage/Admin");
        registry.addViewController("/member").setViewName("adminpage/Member");
        registry.addViewController("/sales").setViewName("adminpage/Sales");
        registry.addViewController("/upload").setViewName("adminpage/Upload");
        registry.addViewController("/saledetails").setViewName("layout/saledetails");
        registry.addViewController("/salepage").setViewName("layout/salepage");
//        registry.addViewController("/admin").setViewName("admin");
    }
}
