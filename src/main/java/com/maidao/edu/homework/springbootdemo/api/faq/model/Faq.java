package com.maidao.edu.homework.springbootdemo.api.faq.model;


import com.maidao.edu.homework.springbootdemo.api.faq.converter.FaqKeywordArrayConverter;
import com.maidao.edu.homework.springbootdemo.api.faq.entity.FaqKeyword;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:Faq
 * 类描述:Faq实体类
 **/
@Entity
@Table(name = "faq")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Convert(converter = FaqKeywordArrayConverter.class)
    @Column(name = "keywords")
    private List<FaqKeyword> keywords;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "status")
    private Byte status;

    @Transient
    private Long replyAt;

    public Faq() {
    }

    public Faq(String title, String content, Long replyAt) {
        this.setTitle(title);
        this.setContent(content);
        this.setReplyAt(replyAt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public List<FaqKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<FaqKeyword> keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getReplyAt() {
        return replyAt;
    }

    public void setReplyAt(Long replyAt) {
        this.replyAt = replyAt;
    }
}
