package com.maidao.edu.news.api.ws.controller;

import com.maidao.edu.news.api.qa.model.QAPaper;
import com.maidao.edu.news.api.article.model.Article;
import com.maidao.edu.news.api.article.qo.ArticleQo;
import com.maidao.edu.news.api.article.service.IArticleService;
import com.maidao.edu.news.api.banner.qo.BannerQo;
import com.maidao.edu.news.api.banner.service.IBannerService;
import com.maidao.edu.news.api.course.model.Course;
import com.maidao.edu.news.api.course.model.Lesson;
import com.maidao.edu.news.api.course.qo.CourseQo;
import com.maidao.edu.news.api.course.qo.CourseWo;
import com.maidao.edu.news.api.course.qo.LessonWo;
import com.maidao.edu.news.api.course.service.ICourseService;
import com.maidao.edu.news.api.faq.service.IFaqService;
import com.maidao.edu.news.api.job.qo.JobQo;
import com.maidao.edu.news.api.job.service.IJobService;
import com.maidao.edu.news.api.partner.model.Partner;
import com.maidao.edu.news.api.partner.qo.PartnerQo;
import com.maidao.edu.news.api.partner.service.IPartnerService;
import com.maidao.edu.news.api.practice.model.Practice;
import com.maidao.edu.news.api.practice.qo.PracticeQo;
import com.maidao.edu.news.api.practice.qo.PracticeWo;
import com.maidao.edu.news.api.practice.service.IPracticeService;
import com.maidao.edu.news.api.qa.service.IQAService;
import com.maidao.edu.news.api.trainer.model.Trainer;
import com.maidao.edu.news.api.trainer.qo.TrainerQo;
import com.maidao.edu.news.api.trainer.service.ITrainerService;
import com.maidao.edu.news.api.trainingproject.service.ITrainingProjectService;
import com.maidao.edu.news.common.controller.Action;
import com.maidao.edu.news.common.controller.ActionSession;
import com.maidao.edu.news.common.controller.BaseController;
import com.maidao.edu.news.common.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Controller
@RequestMapping("/ws/home")
public class HomeController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IPracticeService practiceService;

    @Autowired
    private ITrainerService trainerService;

    @Autowired
    private IQAService qaService;

    @Autowired
    private IPartnerService partnerService;

    @Autowired
    private ITrainingProjectService trainingProjectService;

    @Autowired
    private IFaqService faqService;

    @Autowired
    private IJobService jobService;

    @RequestMapping(value = "/home")
    @Action(session = ActionSession.NONE)
    public ModelAndView home() {
        List<Article> articles = articleService.getTopArticles(3);
        List<Course> courses = courseService.getTopCourse(4);
        List<Trainer> trainers = trainerService.trainers(new TrainerQo());
        List<Partner> partners = partnerService.partners(new PartnerQo(), false);
        return feedback(CollectionUtil.arrayAsMap("articles", articles, "courses", courses, "trainers", trainers, "partners", partners));
    }

    @RequestMapping(value = "/banners")
    @Action(session = ActionSession.NONE)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerService.banners(parseModel(bannerQo, new BannerQo()), false));
    }

    @RequestMapping(value = "/articles")
    @Action(session = ActionSession.NONE)
    public ModelAndView articles(String articleQo) throws Exception {
        return feedback(articleService.articles(parseModel(articleQo, new ArticleQo()), false));
    }

    @RequestMapping(value = "/article")
    @Action(session = ActionSession.NONE)
    public ModelAndView article(Integer id) {
        articleService.addVisit(id);
        return feedback(articleService.article(id));
    }

    @RequestMapping(value = "/courses")
    @Action(session = ActionSession.NONE)
    public ModelAndView courses(String courseQo) throws Exception {
        return feedback(courseService.courses(parseModel(courseQo, new CourseQo())));
    }

    @RequestMapping(value = "/course")
    @Action(session = ActionSession.NONE)
    public ModelAndView course(Integer id) {
        return feedback(courseService.course(id, CourseWo.getUserDetailInstance()));
    }

    @RequestMapping(value = "/lesson")
    @Action(session = ActionSession.NONE)
    public ModelAndView lesson(Integer id) {
        courseService.addPV(id);
        return feedback(courseService.lesson(id, LessonWo.getUserDetailInstance()));
    }

    @RequestMapping(value = "/qaTemplate")
    @Action(session = ActionSession.NONE)
    public ModelAndView qaTemplate(Integer id) {
        return feedback(qaService.qaTemplate(id));
    }

    @RequestMapping(value = "/save_qaPaper")
    @Action(session = ActionSession.NONE)
    public ModelAndView save_qaPaper(String qaPaper) throws Exception {
        qaService.saveQAPaper(parseModel(qaPaper, new QAPaper()));
        return feedback(null);
    }

    @RequestMapping(value = "/practices_by_lessonId")
    @Action(session = ActionSession.NONE)
    public ModelAndView practices_by_lessonId(Integer id) {

        List<Practice> practices = practiceService.practices(new PracticeQo(id), PracticeWo.getNoAnswerInstance());
        Lesson lesson = courseService.lesson(id);

        return feedback(CollectionUtil.arrayAsMap("practices", practices, "lesson", lesson));
    }

    @RequestMapping(value = "/practices_answer_by_lessonId")
    @Action(session = ActionSession.NONE)
    public ModelAndView practices_answer_by_lessonId(Integer id) {
        return feedback(practiceService.practices(new PracticeQo(id), PracticeWo.getNoneInstance()));
    }

    @RequestMapping(value = "/trainingProjects")
    @Action(session = ActionSession.NONE)
    public ModelAndView trainingProjects() {
        return feedback(trainingProjectService.getTopTrainingProjects(4));
    }

    @RequestMapping(value = "/jobs")
    @Action(session = ActionSession.NONE)
    public ModelAndView jobs(String jobQo) throws Exception {
        return feedback(jobService.jobs(parseModel(jobQo, new JobQo())));
    }

    @RequestMapping(value = "/faq_ask")
    @Action(session = ActionSession.NONE)
    public ModelAndView faq_ask(String q) {
        return feedback(faqService.ask(q));
    }
}
