package com.maidao.edu.homework.springbootdemo.api.custeval.qo;

import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CustevalQo
 * 类描述:通过用户评价状态进行查询
 **/

public class CustevalQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    public CustevalQo() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 0 ? null : status;
    }
}
