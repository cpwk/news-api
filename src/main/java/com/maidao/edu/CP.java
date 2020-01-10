package com.maidao.edu;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CP
 * 类描述:项目启动主类
 **/

@SpringBootApplication
@ComponentScan("com.maidao.edu.homework.springbootdemo")
public class CP {

    public static void main(String[] args) {
        SpringApplication.run(CP.class, args);
    }

    public static String[] getScanPackages() {
        return CP.class.getAnnotation(ComponentScan.class).value();
    }

    public static Reflections getAppReflection() {
        return new Reflections(new ConfigurationBuilder().forPackages(getScanPackages()));
    }
}