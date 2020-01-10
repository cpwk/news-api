package com.maidao.edu.homework.springbootdemo.api.term.model;


import com.maidao.edu.homework.springbootdemo.common.entity.Constants;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TODO
 * 类描述:TODO
 **/
@Entity
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "term_index")
    private Byte termIndex;

    @Column(name = "sequence")
    private String sequence;

    @Column(name = "set_default")
    private Byte setDefault;

    @Column(name = "status")
    private Byte status;

    @Transient
    private String asStr;

    public Term() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Byte getSetDefault() {
        return setDefault;
    }

    public void setSetDefault(Byte setDefault) {
        this.setDefault = setDefault;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Byte getTermIndex() {
        return termIndex;
    }

    public void setTermIndex(Byte termIndex) {
        this.termIndex = termIndex;
    }

    public String getAsStr() {
        return year + " 学年第 " + termIndex + " 学期";
    }

    public void setAsStr(String asStr) {
        this.asStr = asStr;
    }
}