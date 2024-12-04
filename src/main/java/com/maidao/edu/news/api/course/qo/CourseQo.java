package com.maidao.edu.news.api.course.qo;

import com.maidao.edu.news.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:CourseQo
 * 类描述:course快速查询
 **/
public class CourseQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "courseType")
    private String courseType;

    @QueryField(type = QueryType.EQUAL, name = "trainerId")
    private Integer trainerId;

    @QueryField(type = QueryType.FULL_LIKE, name = "name")
    private String name;

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Integer status = 1;

    private String sortPropertyName = "priority";

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    @Override
    public String getSortPropertyName() {
        return sortPropertyName;
    }

    @Override
    public void setSortPropertyName(String sortPropertyName) {
        this.sortPropertyName = sortPropertyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status == 0 ? null : status;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId == 0 ? null : trainerId;
    }
}
