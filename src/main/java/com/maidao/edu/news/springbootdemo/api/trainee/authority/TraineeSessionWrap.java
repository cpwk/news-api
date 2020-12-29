package com.maidao.edu.news.springbootdemo.api.trainee.authority;


import com.alibaba.fastjson.annotation.JSONField;
import com.maidao.edu.news.springbootdemo.api.trainee.model.Trainee;
import com.maidao.edu.news.springbootdemo.api.trainee.model.TraineeSession;
import com.maidao.edu.news.springbootdemo.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TraineeSessionWrap implements SessionWrap {

    private Trainee trainee;
    @JSONField(serialize = false)
    private TraineeSession traineeSession;

    public TraineeSessionWrap(Trainee trainee, TraineeSession traineeSession) {
        this.trainee = trainee;
        this.traineeSession = traineeSession;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public TraineeSession getTraineeSession() {
        return traineeSession;
    }

    public void setTraineeSession(TraineeSession traineeSession) {
        this.traineeSession = traineeSession;
    }
}
