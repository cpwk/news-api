package com.maidao.edu.news.api.faq.entity;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:FaqKeyword
 * 类描述:FaqKeyword实体类
 **/
public class FaqKeyword {

    private Byte weight;
    private List<String> words;

    public FaqKeyword() {
    }

    public FaqKeyword(Byte weight, List<String> words) {
        this.weight = weight;
        this.words = words;
    }

    public Byte getWeight() {
        return weight;
    }

    public void setWeight(Byte weight) {
        this.weight = weight;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}

