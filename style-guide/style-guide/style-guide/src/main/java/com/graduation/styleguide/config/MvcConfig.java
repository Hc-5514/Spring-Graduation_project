package com.graduation.styleguide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 요청 - 뷰 연결
    public void addViewControllers(ViewControllerRegistry registry) {

        // admin
        registry.addViewController("/admin").setViewName("admin/Admin");
        registry.addViewController("/member").setViewName("admin/Member");
        registry.addViewController("/sales").setViewName("admin/Sales");
        registry.addViewController("/upload").setViewName("admin/Upload");

        // user
        registry.addViewController("/AdminRegister").setViewName("user/AdminRegister");
        registry.addViewController("/profile").setViewName("user/Profile");
    }
}
