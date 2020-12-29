package com.maidao.edu.news.springbootdemo.api.qa.model;


import com.maidao.edu.news.springbootdemo.api.qa.converter.QAItemArrayConverter;
import com.maidao.edu.news.springbootdemo.api.qa.entity.QAItem;
import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:QATemplate
 * 类描述:实体
 **/
@Entity
@Table(name = "qa_template")
public class QATemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "descr")
    private String descr;

    @Convert(converter = QAItemArrayConverter.class)
    @Column(name = "items")
    private List<QAItem> items;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "status")
    private Byte status;

    public QATemplate() {
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

    public void setStatus(Byte status) throws ServiceException {
        if (status != Constants.STATUS_OK && status != Constants.STATUS_HALT) {
            throw new ServiceException(0);
        }
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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
}