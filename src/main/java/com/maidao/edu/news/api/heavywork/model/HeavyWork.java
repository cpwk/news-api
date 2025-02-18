package com.maidao.edu.news.api.heavywork.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:HeavyWork
 * 类描述:HeavyWork实体类
 **/
@Entity
@Table(name = "heavy_task")
public class HeavyWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "secret")
    private String secret;

    @JSONField(serialize = false)
    @Column(name = "owner")
    private String owner;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "status")
    private Integer status;

    @Column(name = "output")
    private String output;

    @Column(name = "errors")
    private String errors;

    @Column(name = "progress")
    private Integer progress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

}
