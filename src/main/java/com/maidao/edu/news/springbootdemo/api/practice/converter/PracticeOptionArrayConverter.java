package com.maidao.edu.news.springbootdemo.api.practice.converter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.news.springbootdemo.api.practice.entity.PracticeOption;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:PracticeOptionArrayConverter
 * 类描述:转换器
 **/
@Converter(autoApply = true)
public class PracticeOptionArrayConverter implements AttributeConverter<List<PracticeOption>, String> {

    @Override
    public String convertToDatabaseColumn(List<PracticeOption> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<PracticeOption> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, PracticeOption.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
