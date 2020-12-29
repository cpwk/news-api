package com.maidao.edu.news.springbootdemo.api.course.qo;


import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ChapterQo
 * 类描述:Chapter快速查询
 **/
public class ChapterQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "courseId")
    private Integer courseId;

    private String sortPropertyName = "priority";

    public ChapterQo() {
    }


    public ChapterQo(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
