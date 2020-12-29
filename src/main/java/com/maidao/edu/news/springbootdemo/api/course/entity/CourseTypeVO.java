package com.maidao.edu.news.springbootdemo.api.course.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:CourseTypeVO
 * 类描述:CourseType值对象，通常用于业务层之间的数据传递，将枚举类中的数据抽出来
 **/
public class CourseTypeVO {

    private static List<String> types = null;

    public static List<String> getTypes() {
        if (types == null) {
            types = new ArrayList<>();
            for (CourseType type : CourseType.values()) {
                types.add(type.name());
            }
        }
        return types;
    }
}
