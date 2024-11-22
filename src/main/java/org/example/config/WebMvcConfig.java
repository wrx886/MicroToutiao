package org.example.config;

import org.example.interceptor.LoginProtectedInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoginProtectedInterceptor loginProtectedInterceptor;

    public WebMvcConfig(LoginProtectedInterceptor loginProtectedInterceptor) {
        this.loginProtectedInterceptor = loginProtectedInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectedInterceptor)
                .addPathPatterns("/headline/**");
    }
}
