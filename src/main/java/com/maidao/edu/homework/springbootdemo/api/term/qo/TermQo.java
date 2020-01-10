package com.maidao.edu.homework.springbootdemo.api.term.qo;


import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TermQo extends DataQueryObjectSort {

    private String sortPropertyName = "sequence";

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    @Override
    public String getSortPropertyName() {
        return sortPropertyName;
    }

    @Override
    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 0 ? null : status;
    }

}
