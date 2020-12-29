package com.maidao.edu.news.springbootdemo.api.banner.qo;

import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:BannerQo
 * 类描述:banner实体类
 **/

public class BannerQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Integer type;

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    private String sortPropertyName = "priority";

    public BannerQo() {

    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 0 ? null : status;
    }

    @Override
    public String getSortPropertyName() {
        return sortPropertyName;
    }

    @Override
    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

}
