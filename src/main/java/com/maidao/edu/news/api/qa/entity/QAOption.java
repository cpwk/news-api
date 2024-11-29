package com.maidao.edu.news.api.qa.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:QAOption
 * 类描述:实体
 **/
public class QAOption {

    private String label;
    private int isKey; /* 1.答案 2.no */

    public QAOption() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIsKey() {
        return isKey;
    }

    public void setIsKey(int isKey) {
        this.isKey = isKey;
    }
}
