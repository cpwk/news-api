package com.maidao.edu.news.api.course.qo;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:LessonWo
 * 类描述:抛出lesson时方便包装管理
 **/
public class LessonWo {

    private boolean withChapter = false;

    private boolean withChapterLessons = false;


    private boolean withPracticeCount = false;

    public LessonWo() {
    }

    public static LessonWo getNoneInstance() {
        return new LessonWo();
    }

    public static LessonWo getUserDetailInstance() {
        return getNoneInstance().setWithChapterLessons(true).setWithPracticeCount(true);
    }

    public static LessonWo getTrainerDetailInstance() {
        return getNoneInstance().setWithChapter(true);
    }

    public boolean isWithChapter() {
        return withChapter;
    }

    public LessonWo setWithChapter(boolean withChapter) {
        this.withChapter = withChapter;
        return this;
    }

    public boolean isWithPracticeCount() {
        return withPracticeCount;
    }

    public LessonWo setWithPracticeCount(boolean withPracticeCount) {
        this.withPracticeCount = withPracticeCount;
        return this;
    }

    public boolean isWithChapterLessons() {
        return withChapterLessons;
    }

    public LessonWo setWithChapterLessons(boolean withChapterLessons) {
        this.withChapterLessons = withChapterLessons;
        return this;
    }

}
