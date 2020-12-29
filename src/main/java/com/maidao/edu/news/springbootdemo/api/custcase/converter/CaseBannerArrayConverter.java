package com.maidao.edu.news.springbootdemo.api.custcase.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.maidao.edu.news.springbootdemo.api.custcase.entity.CaseBanner;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:CaseBannerArrayConverter
 * 类描述:实现list与JSON之间的互相转换
 **/

@Converter(autoApply = true)
public class CaseBannerArrayConverter implements AttributeConverter<List<CaseBanner>, String> {

    @Override
    public String convertToDatabaseColumn(List<CaseBanner> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<CaseBanner> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, CaseBanner.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
