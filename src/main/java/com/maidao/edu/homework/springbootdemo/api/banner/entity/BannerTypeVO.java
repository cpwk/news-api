package com.maidao.edu.homework.springbootdemo.api.banner.entity;

import com.maidao.edu.homework.springbootdemo.common.entity.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:BannerTypeVO
 * 类描述:BannerType值对象，通常用于业务层之间的数据传递
 **/
public class BannerTypeVO {

    private static List<KeyValue> steps = null;

    public static List<KeyValue> getTypes() {
        if (steps == null) {
            steps = new ArrayList<>();
            for (BannerType type : BannerType.values()) {
                steps.add(new KeyValue(type.getKey(), type.getVal()));
            }
        }
        return steps;
    }

}
