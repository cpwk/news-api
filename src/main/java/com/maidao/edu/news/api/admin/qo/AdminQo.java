package com.maidao.edu.news.api.admin.qo;

import com.maidao.edu.news.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-17 14:16
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdminQo
 * 类描述:实现通过昵称模糊查询
 **/

public class AdminQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "nick")
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
