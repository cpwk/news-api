package com.maidao.edu.homework.springbootdemo.api.course.qo;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CourseWo
 * 类描述:抛出Course数据时进行包装管理
 **/
public class CourseWo {

    private boolean withChapter = false;

    private boolean withBriefLesson = false;

    private boolean withTrainer = false;

    public CourseWo() {
    }

    public static CourseWo getNoneInstance() {
        return new CourseWo();
    }

    public static CourseWo getUserListInstance() {
        return getNoneInstance();
    }

    public static CourseWo getUserDetailInstance() {
        return getNoneInstance().setWithChapter(true).setWithTrainer(true).setWithBriefLesson(true);
    }

    public boolean isWithChapter() {
        return withChapter;
    }

    public CourseWo setWithChapter(boolean withChapter) {
        this.withChapter = withChapter;
        return this;
    }

    public boolean isWithTrainer() {
        return withTrainer;
    }

    public CourseWo setWithTrainer(boolean withTrainer) {
        this.withTrainer = withTrainer;
        return this;
    }

    public boolean isWithBriefLesson() {
        return withBriefLesson;
    }

    public CourseWo setWithBriefLesson(boolean withBriefLesson) {
        this.withBriefLesson = withBriefLesson;
        return this;
    }

}
