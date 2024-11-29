package com.maidao.edu.news.api.news.qo;

import com.maidao.edu.news.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryTable;
import com.maidao.edu.news.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:NewsQo
 * 类描述:实现通过新闻状态，新闻标题进行查询
 **/
@QueryTable()
public class NewsQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.FULL_LIKE, name = "title")
    private String title;

    @QueryField(type = QueryType.EQUAL, name = "status")
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
