package com.maidao.edu.homework.springbootdemo.api.admin.qo;

import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-17 14:16
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdminSessionQo
 * 类描述:实现通过adminID模糊查询
 **/

public class AdminSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "adminId")
    private Integer adminId;

    public AdminSessionQo() {
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
