package com.maidao.edu.news.springbootdemo.api.course.service;


import com.maidao.edu.news.springbootdemo.api.course.model.Chapter;
import com.maidao.edu.news.springbootdemo.api.course.model.Course;
import com.maidao.edu.news.springbootdemo.api.course.model.Lesson;
import com.maidao.edu.news.springbootdemo.api.course.qo.*;
import com.maidao.edu.news.springbootdemo.api.course.repository.IChapterRepository;
import com.maidao.edu.news.springbootdemo.api.course.repository.ICourseRepository;
import com.maidao.edu.news.springbootdemo.api.course.repository.ILessonRepository;
import com.maidao.edu.news.springbootdemo.api.practice.service.IPracticeService;
import com.maidao.edu.news.springbootdemo.api.trainer.service.ITrainerService;
import com.maidao.edu.news.springbootdemo.common.cache.CacheOptions;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheFactory;
import com.maidao.edu.news.springbootdemo.common.cache.KvCacheWrap;
import com.maidao.edu.news.springbootdemo.common.context.Contexts;
import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.exception.RepositoryException;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.sunnysuperman.kvcache.RepositoryProvider;
import com.sunnysuperman.kvcache.converter.BeanModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:CourseService
 * 类描述:course业务逻辑层接口的其中一个实现类
 **/
@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private IChapterRepository chapterRepository;

    @Autowired
    private ILessonRepository lessonRepository;

    @Autowired
    private ITrainerService trainerService;

    @Autowired
    private IPracticeService practiceService;

    @Autowired
    private KvCacheFactory kvCacheFactory;

    private KvCacheWrap<Integer, Course> courseCache;

    @PostConstruct
    public void init() {
        courseCache = kvCacheFactory.create(new CacheOptions("course", 1, Constants.CACHE_REDIS_EXPIRE),
                new RepositoryProvider<Integer, Course>() {

                    @Override
                    public Course findByKey(Integer key) throws RepositoryException {
                        return courseRepository.getOne(key);
                    }

                    @Override
                    public Map<Integer, Course> findByKeys(Collection<Integer> ids) throws RepositoryException {
                        throw new UnsupportedOperationException("findByKeys");
                    }

                }, new BeanModelConverter<>(Course.class));
    }

    @Override
    public Course course(int id, CourseWo wo) {
        Course course = course(id);
        wrapCourse(course, wo);
        return course;
    }

    private Course course(int id) {
        return courseCache.findByKey(id);
    }

    private Course courseByTrainer(int id) throws ServiceException {
        int trainerId = Contexts.requestTrainerId();
        Course course = course(id);
        if (trainerId != course.getTrainerId()) {
            throw new ServiceException(0);
        }
        return course;
    }

    @Override
    public Page<Course> courses(CourseQo qo) {
        return courseRepository.findAll(qo);
    }

    @Override
    public void save_course(Course course) throws ServiceException {

        Integer id = course.getId();

        if (id != null && id > 0) {

            Course oc = courseByTrainer(id);

            oc.setName(course.getName());
            oc.setImg(course.getImg());
            oc.setDescr(course.getDescr());
            oc.setCourseType(course.getCourseType());
            oc.setChapterNum(course.getChapterNum());
            oc.setClassHour(course.getClassHour());
            oc.setVisitNum(course.getVisitNum());
            oc.setPriority(course.getPriority());
            oc.setStatus(course.getStatus());

        } else {

            int trainerId = Contexts.requestTrainerId();

            course.setTrainerId(trainerId);
            course.setCreatedAt(System.currentTimeMillis());
        }

        courseRepository.save(course);
        courseCache.remove(id);
    }

    @Override
    public void course_status(int id, byte status) throws ServiceException {
        Course course = course(id);
        course.setStatus(status);
        courseRepository.save(course);
        courseCache.remove(id);
    }

    @Override
    @Transactional
    public void remove_course(int id) throws ServiceException {
        courseRepository.delete(courseByTrainer(id));
        chapterRepository.deleteByCourseId(id);
        lessonRepository.deleteByCourseId(id);
        courseCache.remove(id);
    }

    @Override
    public List<Course> getTopCourse(int limit) {
        CourseQo qo = new CourseQo();
        qo.setPageSize(limit);

        List<Course> list = courseRepository.findAll(qo).getContent();

        CourseWo wo = CourseWo.getUserListInstance();

        for (Course course : list) {
            wrapCourse(course, wo);
        }

        return list;
    }

    private Course wrapCourse(Course course, CourseWo wo) {

        if (wo.isWithChapter()) {

            List<Chapter> chapters = chapters(new ChapterQo(course.getId()));

            if (wo.isWithBriefLesson()) {
                for (Chapter chapter : chapters) {
                    List<Lesson> lessons = lessons(new LessonQo(chapter.getId()));
                    for (Lesson lesson : lessons) {
                        lesson.setContent(null);
                    }
                    chapter.setLessons(lessons);
                }
            }

            course.setChapters(chapters);
        }

        if (wo.isWithTrainer()) {
            course.setTrainer(trainerService.trainer(course.getTrainerId(), false));
        }

        return course;
    }

    @Override
    public Chapter chapter(int id) {
        Chapter chapter = chapterRepository.getOne(id);
        chapter.setCourse(course(chapter.getCourseId()));
        return chapter;
    }

    private Chapter chapterByTrainer(int id) throws ServiceException {
        Chapter chapter = chapter(id);
        courseByTrainer(chapter.getCourseId());
        return chapter;
    }

    @Override
    public List<Chapter> chapters(ChapterQo qo) {
        return chapterRepository.findAll(qo);
    }

    @Override
    public void save_chapter(Chapter chapter) throws ServiceException {
        Integer id = chapter.getId();
        courseByTrainer(chapter.getCourseId());
        if (id != null && id > 0) {
            Chapter oc = chapter(id);
            oc.setName(chapter.getName());
            oc.setLessonNum(chapter.getLessonNum());
            oc.setClassHour(chapter.getClassHour());
            oc.setPriority(chapter.getPriority());

        } else {
            chapter.setCreatedAt(System.currentTimeMillis());
        }
        chapterRepository.save(chapter);
    }

    @Override
    @Transactional
    public void remove_chapter(int id) throws ServiceException {
        chapterByTrainer(id);
        chapterRepository.deleteById(id);
        lessonRepository.deleteByChapterId(id);
    }

    @Override
    public Lesson lesson(int id) {
        return lessonRepository.getOne(id);
    }

    @Override
    public Lesson lesson(int id, LessonWo wo) {

        Lesson lesson = lesson(id);
        wrapLesson(lesson, wo);
        return lesson;
    }

    private void wrapLesson(Lesson lesson, LessonWo wo) {

        if (wo.isWithChapter()) {
            lesson.setChapter(chapter(lesson.getChapterId()));
        }

        if (wo.isWithChapterLessons()) {
            List<Lesson> lessons = lessons(new LessonQo(lesson.getChapterId()));
            int size = lessons.size();
            if (size > 1) {
                for (int i = 0; i < size; i++) {
                    Lesson tmp = lessons.get(i);
                    if (lesson.getId().intValue() == tmp.getId().intValue()) {
                        if (i > 0) {
                            lesson.setPrevLesson(lessons.get(i - 1));
                        }
                        if (i < size - 1) {
                            lesson.setNextLesson(lessons.get(i + 1));
                        }
                        break;
                    }
                }
            }
            List<Lesson> simpleLessons = new ArrayList<>(size);
            for (Lesson lesson1 : lessons) {
                simpleLessons.add(new Lesson(lesson1.getId(), lesson1.getName()));
            }

            lesson.setLessons(simpleLessons);

        }
        if (wo.isWithPracticeCount()) {
            lesson.setPracticeCount(practiceService.countByLessonId(lesson.getId()));
        }

    }

    @Override
    public List<Lesson> lessons(LessonQo qo) {
        return lessonRepository.findAll(qo);
    }

    @Override
    public void save_lesson(Lesson lesson) throws ServiceException {
        Integer id = lesson.getId();
        chapterByTrainer(lesson.getChapterId());
        if (id != null && id > 0) {
            Lesson oc = lesson(id);
            oc.setName(lesson.getName());
            oc.setClassHour(lesson.getClassHour());
            oc.setRequirement(lesson.getRequirement());
            oc.setKeyPoint(lesson.getKeyPoint());
            oc.setContent(lesson.getContent());
            oc.setPv(lesson.getPv());
            oc.setPriority(lesson.getPriority());
        } else {
            lesson.setCreatedAt(System.currentTimeMillis());
        }
        lessonRepository.save(lesson);
    }

    private Lesson lessonByTrainer(int id) throws ServiceException {
        Lesson lesson = lesson(id);
        chapterByTrainer(lesson.getChapterId());
        return lesson;
    }

    @Override
    public void remove_lesson(int id) throws ServiceException {
        lessonByTrainer(id);
        lessonRepository.deleteById(id);
    }

    @Override
    public void addPV(int id) {
        Lesson lesson = lesson(id);
        lesson.setPv(lesson.getPv() + 1);
        lessonRepository.save(lesson);
    }
}
