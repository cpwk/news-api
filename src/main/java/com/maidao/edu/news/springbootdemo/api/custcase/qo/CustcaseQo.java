package com.maidao.edu.news.springbootdemo.api.custcase.qo;

import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:CustcaseQo
 * 类描述:通过用户案例的status,ontop,offtop进行查询
 **/

public class CustcaseQo extends DataQueryObjectPage {

    private String sortPropertyName = "priority";

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    @QueryField(type = QueryType.EQUAL, name = "settop")
    private Integer ontop;

    @QueryField(type = QueryType.EQUAL, name = "settop")
    private Integer offtop;

    public CustcaseQo() {
    }

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

    public Integer getOntop() {
        return ontop;
    }

    public void setOntop(Integer ontop) {
        this.ontop = ontop == 0 ? null : ontop;
    }

    public Integer getOfftop() {
        return offtop;
    }

    public void setOfftop(Integer offtop) {
        this.offtop = offtop == 0 ? null : offtop;
    }
}
