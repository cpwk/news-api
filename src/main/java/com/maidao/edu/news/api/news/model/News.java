package com.maidao.edu.news.api.news.model;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-30 09:54
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:News
 * 类描述:新闻实体类
 **/

@Entity
@Table
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String title;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "click_num")
    private Integer clickNum;
    @Column
    private Integer status;
    @Column
    private String content;

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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
