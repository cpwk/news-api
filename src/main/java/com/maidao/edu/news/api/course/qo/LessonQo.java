package com.maidao.edu.news.api.course.qo;


import com.maidao.edu.news.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:LessonQo
 * 类描述:lesson快速查询
 **/
public class LessonQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "chapterId")
    private Integer chapterId;

    private String sortPropertyName = "priority";

    public LessonQo() {
    }

    public LessonQo(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
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
