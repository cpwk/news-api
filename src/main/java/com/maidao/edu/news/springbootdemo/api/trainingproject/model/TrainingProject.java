package com.maidao.edu.news.springbootdemo.api.trainingproject.model;


import com.maidao.edu.news.springbootdemo.common.converter.StringArrayConverter;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Entity
@Table(name = "training_project")
public class TrainingProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "img")
    private String img;

    @Column(name = "descr")
    private String descr;

    @Column(name = "key_point")
    @Convert(converter = StringArrayConverter.class)
    private List<String> keyPoint;

    @Column(name = "content")
    private String content;

    @Column(name = "settop")
    private Integer settop;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "status")
    private Byte status;


    public TrainingProject() {
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<String> getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(List<String> keyPoint) {
        this.keyPoint = keyPoint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSettop() {
        return settop;
    }

    public void setSettop(Integer settop) {
        this.settop = settop;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
