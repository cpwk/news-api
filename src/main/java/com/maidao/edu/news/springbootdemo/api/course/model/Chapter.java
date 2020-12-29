package com.maidao.edu.news.springbootdemo.api.course.model;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Chapter
 * 类描述:Chapter实体类
 **/
@Entity
@Table(name = "chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "lesson_num")
    private Byte lessonNum;

    @Column(name = "class_hour")
    private Byte classHour;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "priority")
    private Integer priority;

    @Transient
    private Course course;

    @Transient
    private List<Lesson> lessons;

    public Chapter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Byte getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Byte lessonNum) {
        this.lessonNum = lessonNum;
    }

    public Byte getClassHour() {
        return classHour;
    }

    public void setClassHour(Byte classHour) {
        this.classHour = classHour;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
