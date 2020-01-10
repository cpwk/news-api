package com.maidao.edu.homework.springbootdemo.common.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:Action
 * 类描述:自定义springboot注解，用于请求接口时验证请求者的token是否失效
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

    ActionSession[] session();

    ActionResultType result() default ActionResultType.Json;
}
