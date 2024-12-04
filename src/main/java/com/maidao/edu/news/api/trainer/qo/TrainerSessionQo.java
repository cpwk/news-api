package com.maidao.edu.news.api.trainer.qo;


import com.maidao.edu.news.common.reposiotry.support.QueryField;
import com.maidao.edu.news.common.reposiotry.support.QueryType;
import com.maidao.edu.news.common.reposiotry.support.DataQueryObjectPage;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TrainerSessionQo extends DataQueryObjectPage {

    @QueryField(type = QueryType.EQUAL, name = "trainerId")
    private Integer trainerId;

    public TrainerSessionQo() {
    }

    public TrainerSessionQo(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }
}
