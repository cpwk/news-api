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
 * 项目名称：news
 * 类名称:CP
 * 类描述:项目启动主类
 **/

@SpringBootApplication
@ComponentScan("com.maidao.edu.news.springbootdemo")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static String[] getScanPackages() {
        return Application.class.getAnnotation(ComponentScan.class).value();
    }

    public static Reflections getAppReflection() {
        return new Reflections(new ConfigurationBuilder().forPackages(getScanPackages()));
    }
}