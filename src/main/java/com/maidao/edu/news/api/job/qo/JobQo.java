package com.maidao.edu.news.api.job.qo;


import com.maidao.edu.news.common.reposiotry.support.DataQueryObjectSort;
import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:JobQo
 * 类描述:快查
 **/
public class JobQo extends DataQueryObjectSort {

    @QueryField(type = QueryType.EQUAL, name = "type")
    private Integer type;

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status = 1;

    private String sortPropertyName = "priority";

    public JobQo() {

    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status == 0 ? null : status;
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
