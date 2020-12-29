package com.maidao.edu.news.springbootdemo.api.faq.qo;


import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:FaqQo
 * 类描述:Faq快速查询
 **/
public class FaqQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status;

    public FaqQo() {
    }

    public FaqQo(Byte status) {
        this.status = status;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
