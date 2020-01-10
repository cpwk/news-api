package com.maidao.edu.homework.springbootdemo.api.qa.model;


import com.maidao.edu.homework.springbootdemo.api.qa.converter.QAItemArrayConverter;
import com.maidao.edu.homework.springbootdemo.api.qa.entity.QAItem;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:QAPaper
 * 类描述:实体
 **/
@Entity
@Table(name = "qa_paper")
public class QAPaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tpl_id")
    private Integer tplId;

    @Convert(converter = QAItemArrayConverter.class)
    @Column(name = "items")
    private List<QAItem> items;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "deal_at")
    private Long dealAt;

    @Column(name = "status")
    private Byte status;

    public QAPaper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getTplId() {
        return tplId;
    }

    public void setTplId(Integer tplId) {
        this.tplId = tplId;
    }

    public List<QAItem> getItems() {
        return items;
    }

    public void setItems(List<QAItem> items) {
        this.items = items;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDealAt() {
        return dealAt;
    }

    public void setDealAt(Long dealAt) {
        this.dealAt = dealAt;
    }
}