package com.maidao.edu.news.api.qa.entity;


import com.maidao.edu.news.common.converter.StringArrayConverter;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:QAItem
 * 类描述:实体
 **/
public class QAItem {

    private String type;

    private String question;

    @Convert(converter = StringArrayConverter.class)
    private List<String> options;/* 选项 */

    @Convert(converter = StringArrayConverter.class)
    private List<String> values;

    private Byte required;  /* 1.yes 2.no */

    public QAItem() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getValues() {
        return values == null ? new ArrayList<>() : values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Byte getRequired() {
        return required;
    }

    public void setRequired(Byte required) {
        this.required = required;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
