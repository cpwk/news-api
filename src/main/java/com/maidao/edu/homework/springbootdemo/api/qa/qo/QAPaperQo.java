package com.maidao.edu.homework.springbootdemo.api.qa.qo;


import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:QAPaperQo
 * 类描述:快查
 **/
public class QAPaperQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "status")
    private Byte status = 0;

    @QueryField(type = QueryType.EQUAL, name = "tplId")
    private Integer tplId;

    public QAPaperQo() {
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status == 0 ? null : status;
    }

    public Integer getTplId() {
        return tplId;
    }

    public void setTplId(Integer tplId) {
        this.tplId = tplId == 0 ? null : tplId;
    }

}
