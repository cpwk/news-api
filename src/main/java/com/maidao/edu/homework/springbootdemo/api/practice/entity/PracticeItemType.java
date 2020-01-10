package com.maidao.edu.homework.springbootdemo.api.practice.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:PracticeItemType
 * 类描述:类型枚举
 **/
public enum PracticeItemType {

    INPUT("input", "单行输入"), TEXTAREA("textarea", "多行输入"),
    SELECT("select", "单选"), MSELECT("mselect", "多选");

    private String key;
    private String label;

    PracticeItemType(String key, String label) {
        this.setKey(key);
        this.setLabel(label);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
