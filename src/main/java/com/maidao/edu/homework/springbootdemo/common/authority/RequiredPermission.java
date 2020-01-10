package com.maidao.edu.homework.springbootdemo.common.authority;

import com.maidao.edu.homework.springbootdemo.api.admin.authority.AdminPermission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:RequiredPermission
 * 类描述:TODO
 **/

@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RequiredPermission {

    AdminType[] adminType();

    AdminPermission[] adminPermission() default AdminPermission.NONE;
}
