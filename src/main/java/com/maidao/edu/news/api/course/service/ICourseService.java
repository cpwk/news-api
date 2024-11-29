package com.maidao.edu.news.api.course.service;


import com.maidao.edu.news.api.course.model.Chapter;
import com.maidao.edu.news.api.course.qo.ChapterQo;
import com.maidao.edu.news.api.course.qo.CourseQo;
import com.maidao.edu.news.api.course.qo.CourseWo;
import com.maidao.edu.news.api.course.qo.LessonQo;
import com.maidao.edu.news.api.course.qo.LessonWo;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.api.course.model.Course;
import com.maidao.edu.news.api.course.model.Lesson;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ICourseService
 * 类描述:course业务逻辑层接口
 **/
public interface ICourseService {

    //course
    Course course(int id, CourseWo wo);

    Page<Course> courses(CourseQo qo);

    void save_course(Course course) throws ServiceException;

    void course_status(int id, byte status) throws ServiceException;

    void remove_course(int id) throws ServiceException;

    List<Course> getTopCourse(int limit);

    //chapter
    Chapter chapter(int id);

    List<Chapter> chapters(ChapterQo qo);

    void save_chapter(Chapter chapter) throws ServiceException;

    void remove_chapter(int id) throws ServiceException;


    //lesson
    Lesson lesson(int id);

    Lesson lesson(int id, LessonWo wo);

    List<Lesson> lessons(LessonQo qo);

    void save_lesson(Lesson lesson) throws ServiceException;

    void remove_lesson(int id) throws ServiceException;

    void addPV(int id);

}
