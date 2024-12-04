package com.maidao.edu.news.api.job.model;


import com.maidao.edu.news.common.converter.StringArrayConverter;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Job
 * 类描述:实体
 **/
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "pay")
    private String pay;

    @Column(name = "location")
    private String location;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "responsibility")
    private List<String> responsibility;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "skill")
    private List<String> skill;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Byte status;

    public Job() {
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public List<String> getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(List<String> responsibility) {
        this.responsibility = responsibility;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
