package com.maidao.edu.homework.springbootdemo.common.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:WebAppConfig
 * 类描述:TODO
 **/

@Configuration
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor apiInterceptor() {
        return new DefaultInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }
}
