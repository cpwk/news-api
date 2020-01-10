package com.maidao.edu.homework.springbootdemo.api.article.model;


import com.maidao.edu.homework.springbootdemo.common.util.SimpleHtmlParser;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:Article
 * 类描述:Article实体类
 **/
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "intro")
    private String intro;

    @Column(name = "content")
    private String content = "";

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "status")
    private Byte status;

    @Column(name = "visit_num")
    private Integer visitNum;

    public Article() {
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return SimpleHtmlParser.removeScript(content);
    }

    public void setContent(String content) {
        this.content = SimpleHtmlParser.removeScript(content);
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
