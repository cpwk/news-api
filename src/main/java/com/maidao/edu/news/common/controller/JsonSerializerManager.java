package com.maidao.edu.news.common.controller;

import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.sunnysuperman.commons.util.JSONUtil;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:JsonSerializerManager
 * 类描述:TODO
 **/
public class JsonSerializerManager {
    private static final Map<Type, ObjectSerializer> serializers = new HashMap<>();

    public static void register(Type type, ObjectSerializer serializer) {
        synchronized (serializers) {
            serializers.put(type, serializer);
        }
    }

    public static String serialize(Object result) {
        return JSONUtil.toJSONString(result, serializers);
    }

}
