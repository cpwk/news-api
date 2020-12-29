package com.maidao.edu.news.springbootdemo.common.region;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Converter(autoApply = true)
public class RegionArrayConverter implements AttributeConverter<List<Region>, String> {

    @Override
    public String convertToDatabaseColumn(List<Region> list) {
        return JSON.toJSONString(list);
    }

    @Override
    public List<Region> convertToEntityAttribute(String data) {
        try {
            return JSONArray.parseArray(data, Region.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
