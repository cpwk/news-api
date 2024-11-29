package com.maidao.edu.news.api.course.model;


import com.maidao.edu.news.api.trainer.model.Trainer;
import com.maidao.edu.news.common.entity.Constants;
import com.maidao.edu.news.common.exception.ServiceException;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Course
 * 类描述:Course实体类
 **/
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "trainer_id")
    private Integer trainerId;

    @Column(name = "name")
    private String name;

    @Column(name = "img")
    private String img;

    @Column(name = "descr")
    private String descr;

    @Column(name = "content")
    private String content;

    @Column(name = "course_type")
    private String courseType;

    @Column(name = "chapter_num")
    private Byte chapterNum;

    @Column(name = "class_hour")
    private Byte classHour;

    @Column(name = "visit_num")
    private Byte visitNum;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "priority")
    private Byte priority;

    @Column(name = "status")
    private Byte status;

    @Transient
    private List<Chapter> chapters;

    @Transient
    private Trainer trainer;

    public Course() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Byte chapterNum) {
        this.chapterNum = chapterNum;
    }

    public Byte getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Byte visitNum) {
        this.visitNum = visitNum;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) throws ServiceException {
        if (status != Constants.STATUS_OK && status != Constants.STATUS_HALT) {
            throw new ServiceException(0);
        }
        this.status = status;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Byte getClassHour() {
        return classHour;
    }

    public void setClassHour(Byte classHour) {
        this.classHour = classHour;
    }
}
