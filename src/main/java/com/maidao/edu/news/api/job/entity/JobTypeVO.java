package com.maidao.edu.news.api.job.entity;


import com.maidao.edu.news.common.entity.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:JobTypeVO
 * 类描述:值对象，通常用于业务层之间的数据传递，将枚举类中的数据抽出来
 **/
public class JobTypeVO {

    private static List<KeyValue> types = null;

    public static List<KeyValue> getTypes() {
        if (types == null) {
            types = new ArrayList<>();
            for (JobType type : JobType.values()) {
                types.add(new KeyValue(type.getKey(), type.getVal()));
            }
        }
        return types;
    }

}
