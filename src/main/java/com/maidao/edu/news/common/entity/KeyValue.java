package com.maidao.edu.news.common.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:KeyValue
 * 类描述:TODO
 **/
public class KeyValue {
    private String keys;
    private Integer key;
    private String val;

    public KeyValue() {
    }

    public KeyValue(String keys, String val) {
        this.keys = keys;
        this.val = val;
    }

    public KeyValue(Integer key, String val) {
        this.key = key;
        this.val = val;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
