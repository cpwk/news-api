package com.maidao.edu.news.springbootdemo.api.course.model;


import com.maidao.edu.news.springbootdemo.common.converter.StringArrayConverter;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Lesson
 * 类描述:Lesson实体类
 **/
@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "chapter_id")
    private Integer chapterId;

    @Column(name = "name")
    private String name;

    @Column(name = "class_hour")
    private Byte classHour;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "requirement")
    private List<String> requirement;

    @Convert(converter = StringArrayConverter.class)
    @Column(name = "key_point")
    private List<String> keyPoint;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "pv")
    private Integer pv;

    @Column(name = "priority")
    private Integer priority;

    @Transient
    private Chapter chapter;

    @Transient
    private Integer practiceCount;

    @Transient
    private List<Lesson> lessons;

    @Transient
    private Lesson prevLesson;

    @Transient
    private Lesson nextLesson;

    public Lesson() {
    }

    public Lesson(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Byte getClassHour() {
        return classHour;
    }

    public void setClassHour(Byte classHour) {
        this.classHour = classHour;
    }

    public List<String> getRequirement() {
        return requirement;
    }

    public void setRequirement(List<String> requirement) {
        this.requirement = requirement;
    }

    public List<String> getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(List<String> keyPoint) {
        this.keyPoint = keyPoint;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getPv() {
        return pv == null ? 0 : pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getPracticeCount() {
        return practiceCount;
    }

    public void setPracticeCount(Integer practiceCount) {
        this.practiceCount = practiceCount;
    }

    public Lesson getPrevLesson() {
        return prevLesson;
    }

    public void setPrevLesson(Lesson prevLesson) {
        this.prevLesson = prevLesson;
    }

    public Lesson getNextLesson() {
        return nextLesson;
    }

    public void setNextLesson(Lesson nextLesson) {
        this.nextLesson = nextLesson;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
