package com.maidao.edu.homework.springbootdemo.api.faq.converter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.homework.springbootdemo.api.faq.entity.FaqKeyword;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:FaqKeywordArrayConverter
 * 类描述:实现list与JSON之间的互相转换
 **/
@Converter(autoApply = true)
public class FaqKeywordArrayConverter implements AttributeConverter<List<FaqKeyword>, String> {

    @Override
    public String convertToDatabaseColumn(List<FaqKeyword> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<FaqKeyword> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, FaqKeyword.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
