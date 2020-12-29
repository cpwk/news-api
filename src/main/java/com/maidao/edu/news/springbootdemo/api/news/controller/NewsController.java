package com.maidao.edu.news.springbootdemo.api.news.controller;

import com.maidao.edu.news.springbootdemo.api.news.model.News;
import com.maidao.edu.news.springbootdemo.api.news.qo.NewsQo;
import com.maidao.edu.news.springbootdemo.api.news.service.NewsService;
import com.maidao.edu.news.springbootdemo.common.controller.Action;
import com.maidao.edu.news.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.news.springbootdemo.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-30 09:54
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:NewsController
 * 类描述:新闻接口
 **/

@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "findbyid")
    @Action(session = ActionSession.NONE)
    public ModelAndView onenews(Integer id) throws Exception {
        return feedback(newsService.findById(id));
    }

    @RequestMapping(value = "/findall")
    @Action(session = ActionSession.NONE)
    public ModelAndView news(String newsQo) throws Exception {
        return feedback(newsService.findAll(parseModel(newsQo, new NewsQo())));
    }

    @RequestMapping(value = "save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String news) throws Exception {
        newsService.save(parseModel(news, new News()));
        return feedback();
    }

    @RequestMapping(value = "deletebyid")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView deletenews(Integer id) throws Exception {
        System.out.println(id);
        newsService.deleteById(id);
        return feedback();
    }

    @RequestMapping(value = "deleteall")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView deleteAll() throws Exception {
        newsService.deleteAll();
        return feedback();
    }
}
