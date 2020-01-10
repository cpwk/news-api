package com.maidao.edu.homework.springbootdemo.api.qa.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.homework.springbootdemo.api.qa.entity.QAOption;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:QAOptionArrayConverter
 * 类描述:转换器
 **/
@Converter(autoApply = true)
public class QAOptionArrayConverter implements AttributeConverter<List<QAOption>, String> {

    @Override
    public String convertToDatabaseColumn(List<QAOption> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<QAOption> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, QAOption.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
