package com.maidao.edu.news.springbootdemo.api.trainer.qo;


import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TrainerQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    private boolean sortAscending = true;

    public TrainerQo() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean isSortAscending() {
        return sortAscending;
    }

    @Override
    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
    }
}
