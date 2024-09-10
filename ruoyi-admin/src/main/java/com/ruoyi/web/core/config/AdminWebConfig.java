package com.ruoyi.web.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/user/**");  //所有请求都被拦截包括静态资源
    }
}