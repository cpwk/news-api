package com.maidao.edu.homework.springbootdemo.api.course.controller;


import com.maidao.edu.homework.springbootdemo.api.course.entity.CourseTypeVO;
import com.maidao.edu.homework.springbootdemo.api.course.model.Chapter;
import com.maidao.edu.homework.springbootdemo.api.course.model.Course;
import com.maidao.edu.homework.springbootdemo.api.course.model.Lesson;
import com.maidao.edu.homework.springbootdemo.api.course.qo.*;
import com.maidao.edu.homework.springbootdemo.api.course.service.ICourseService;
import com.maidao.edu.homework.springbootdemo.common.context.Contexts;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TrainerCourseController
 * 类描述:course控制层接口
 **/
@Controller
@RequestMapping("/trainer/course")
public class TrainerCourseController extends BaseController {

    @Autowired
    private ICourseService courseService;

    private List<String> courseTypes = CourseTypeVO.getTypes();

    @RequestMapping(value = "/course_types")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView course_types() {
        return feedback(courseTypes);
    }

    @RequestMapping(value = "/save_course")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_course(String course) throws Exception {
        courseService.save_course(parseModel(course, new Course()));
        return feedback(null);
    }

    @RequestMapping(value = "/courses")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView courses(String courseQo) throws ServiceException {
        CourseQo qo = parseModel(courseQo, new CourseQo());
        qo.setTrainerId(Contexts.requestTrainerId());
        return feedback(courseService.courses(qo));
    }

    @RequestMapping(value = "/course")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView course(Integer id) throws ServiceException {
        return feedback(courseService.course(id, CourseWo.getNoneInstance()));
    }

    @RequestMapping(value = "/course_status")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView course_status(Integer id, Byte status) throws ServiceException {
        courseService.course_status(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/remove_course")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_course(Integer id) throws ServiceException {
        courseService.remove_course(id);
        return feedback(null);
    }

    @RequestMapping(value = "/chapters")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView chapters(String chapterQo) throws ServiceException {
        return feedback(courseService.chapters(parseModel(chapterQo, new ChapterQo())));
    }

    @RequestMapping(value = "/chapter")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView chapter(Integer id) throws ServiceException {
        return feedback(courseService.chapter(id));
    }

    @RequestMapping(value = "/save_chapter")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_chapter(String chapter) throws Exception {
        courseService.save_chapter(parseModel(chapter, new Chapter()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove_chapter")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_chapter(Integer id) throws ServiceException {
        courseService.remove_chapter(id);
        return feedback(null);
    }

    @RequestMapping(value = "/lessons")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView lessons(String lessonQo) throws ServiceException {
        return feedback(courseService.lessons(parseModel(lessonQo, new LessonQo())));
    }

    @RequestMapping(value = "/lesson")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView lesson(Integer id) throws ServiceException {
        return feedback(courseService.lesson(id));
    }

    @RequestMapping(value = "/wraped_lesson")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView wraped_lesson(Integer id) throws ServiceException {
        return feedback(courseService.lesson(id, LessonWo.getTrainerDetailInstance()));
    }

    @RequestMapping(value = "/save_lesson")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_lesson(String lesson) throws Exception {
        courseService.save_lesson(parseModel(lesson, new Lesson()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove_lesson")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_lesson(Integer id) throws ServiceException {
        courseService.remove_lesson(id);
        return feedback(null);
    }
}