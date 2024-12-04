package com.maidao.edu.news.api.practice.model;


import com.maidao.edu.news.api.practice.converter.PracticeOptionArrayConverter;
import com.maidao.edu.news.api.practice.entity.PracticeOption;

import javax.persistence.*;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Practice
 * 类描述:实体
 **/
@Entity
@Table(name = "practice")
public class Practice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "question")
    private String question;

    @Convert(converter = PracticeOptionArrayConverter.class)
    @Column(name = "options")
    private List<PracticeOption> options;

    @Column(name = "answer")
    private String answer;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "chapter_id")
    private Integer chapterId;

    @Column(name = "lesson_id")
    private Integer lessonId;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "score")
    private Integer score;

    @Column(name = "priority")
    private Integer priority;

    public Practice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<PracticeOption> getOptions() {
        return options;
    }

    public void setOptions(List<PracticeOption> options) {
        this.options = options;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        if (score <= 0) {
            score = 1;
        }
        if (score > 5) {
            score = 5;
        }
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
