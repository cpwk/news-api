package com.maidao.edu.homework.springbootdemo.api.practice.qo;


import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:PracticeQo
 * 类描述:快查
 **/
public class PracticeQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "chapterId")
    private Integer chapterId;

    @QueryField(type = QueryType.EQUAL, name = "lessonId")
    private Integer lessonId;

    private String sortPropertyName = "priority";


    public PracticeQo() {
    }

    public PracticeQo(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId == 0 ? null : chapterId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId == 0 ? null : lessonId;
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
