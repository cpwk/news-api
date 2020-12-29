package com.maidao.edu.news.springbootdemo.api.practice.qo;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:PracticeWo
 * 类描述:抛出时方便包装管理
 **/
public class PracticeWo {

    private boolean withoutAnswer = false;

    public PracticeWo() {
    }

    public static PracticeWo getNoneInstance() {
        return new PracticeWo();
    }

    public static PracticeWo getNoAnswerInstance() {
        return getNoneInstance().setWithoutAnswer(true);
    }

    public boolean isWithoutAnswer() {
        return withoutAnswer;
    }

    public PracticeWo setWithoutAnswer(boolean withoutAnswer) {
        this.withoutAnswer = withoutAnswer;
        return this;
    }


}
