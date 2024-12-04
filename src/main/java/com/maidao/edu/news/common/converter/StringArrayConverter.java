package com.maidao.edu.news.common.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:StringArrayConverter
 * 类描述:TODO
 **/
@Converter(autoApply = true)
public class StringArrayConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
