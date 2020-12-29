package com.maidao.edu.news.springbootdemo.api.job.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:JobType
 * 类描述:讲师类型枚举
 **/
public enum JobType {
    TRAINER(1, "讲师"), IT(2, "开发");

    private int key;
    private String val;

    JobType(int key, String val) {
        this.key = key;
        this.val = val;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
