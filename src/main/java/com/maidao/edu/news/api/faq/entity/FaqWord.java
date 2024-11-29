package com.maidao.edu.news.api.faq.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:FaqWord
 * 类描述:FaqWord实体类
 **/
public class FaqWord {

    private int faqId;
    private int weight;
    private String word;

    public FaqWord() {
        super();
    }

    public FaqWord(int faqId, int weight, String word) {
        super();
        this.faqId = faqId;
        this.weight = weight;
        this.word = word;
    }

    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
