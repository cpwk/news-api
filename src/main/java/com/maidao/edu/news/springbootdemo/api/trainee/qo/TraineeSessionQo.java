package com.maidao.edu.news.springbootdemo.api.trainee.qo;


import com.maidao.edu.news.springbootdemo.common.reposiotry.support.DataQueryObjectPage;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryField;
import com.maidao.edu.news.springbootdemo.common.reposiotry.support.QueryType;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TraineeSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "traineeId")
    private Integer traineeId;

    public TraineeSessionQo() {
    }

    public TraineeSessionQo(Integer traineeId) {
        this.traineeId = traineeId;
    }

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
    }
}
