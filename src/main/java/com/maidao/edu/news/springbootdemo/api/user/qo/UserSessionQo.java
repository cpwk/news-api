package com.maidao.edu.news.springbootdemo.api.user.qo;

import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:UserSessionQo
 * 类描述:通过用户ID对用户会话记录进行查询
 **/

public class UserSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "userId")
    private Integer userId;

    public UserSessionQo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
