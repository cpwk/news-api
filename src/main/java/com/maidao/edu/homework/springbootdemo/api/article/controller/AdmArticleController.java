package com.maidao.edu.homework.springbootdemo.api.article.controller;

import com.maidao.edu.homework.springbootdemo.api.article.model.Article;
import com.maidao.edu.homework.springbootdemo.api.article.qo.ArticleQo;
import com.maidao.edu.homework.springbootdemo.api.article.service.IArticleService;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdmArticleController
 * 类描述:Article控制层接口
 **/
@Controller
@RequestMapping("/adm/article")
public class AdmArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String article) throws Exception {
        articleService.save(parseModel(article, new Article()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) throws Exception {
        articleService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/article")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView article(Integer id) throws Exception {
        return feedback(articleService.article(id));
    }

    @RequestMapping(value = "/articles")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView articles(String articleQo) throws Exception {
        return feedback(articleService.articles(parseModel(articleQo, new ArticleQo()), true));
    }

}