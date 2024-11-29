package com.maidao.edu.news.api.trainer.authority;

import com.alibaba.fastjson.annotation.JSONField;
import com.maidao.edu.news.api.trainer.model.Trainer;
import com.maidao.edu.news.api.trainer.model.TrainerSession;
import com.maidao.edu.news.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TrainerSessionWrap implements SessionWrap {

    private Trainer trainer;
    @JSONField(serialize = false)
    private TrainerSession trainerSession;

    public TrainerSessionWrap(Trainer trainer, TrainerSession trainerSession) {
        this.trainer = trainer;
        this.trainerSession = trainerSession;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public TrainerSession getTrainerSession() {
        return trainerSession;
    }

    public void setTrainerSession(TrainerSession trainerSession) {
        this.trainerSession = trainerSession;
    }
}
