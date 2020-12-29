package com.maidao.edu.news.springbootdemo.api.admin.authority;

import com.maidao.edu.news.springbootdemo.common.authority.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdminPermissionVO
 * 类描述:将枚举的权限转换成List数组
 **/

public class AdminPermissionVO extends Permission {

    private static List<Permission> list = null;

    public static List<Permission> initPermissions() {
        if (list == null) {
            list = new ArrayList<>();
            for (AdminPermission p : AdminPermission.values()) {
                list.add(new Permission(p.name(), p.getVal(), p.getLevel()));
            }
        }
        return list;
    }

    public static List<Permission> initPermissionsByPs(String ps) {
        List<Permission> list = initPermissions();
        List<Permission> result = new ArrayList<Permission>();
        String[] pl = ps.split(",");
        if (pl.length > 0) {
            for (String s : pl) {
                for (Permission p : list) {
                    if (s.equals(p.getCode()))
                        result.add(p);
                }
            }
        }
        return result;
    }
}