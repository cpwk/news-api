package com.maidao.edu.news.api.qa.entity;


import com.maidao.edu.news.common.entity.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:FormItemTypeVO
 * 类描述:值对象，通常用于业务层之间的数据传递，将枚举类中的数据抽出来
 **/
public class FormItemTypeVO {

    private static List<KeyValue> steps = null;

    public static List<KeyValue> getTypes() {
        if (steps == null) {
            steps = new ArrayList<>();
            for (FormItemType type : FormItemType.values()) {
                steps.add(new KeyValue(type.getKey(), type.getLabel()));
            }
        }
        return steps;
    }

}
