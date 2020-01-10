package com.maidao.edu.homework.springbootdemo.api.faq.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:SimpleFaq
 * 类描述:SimpleFaq实体类
 **/
public class SimpleFaq {

    private String id;
    private String content;
    private Long replyAt;

    public SimpleFaq() {
    }

    public SimpleFaq(String content) {
        this.setContent(content);
    }

    public SimpleFaq(String id, String content) {
        this.setId(id);
        this.setContent(content);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReplyAt() {
        return System.currentTimeMillis();
    }

}
