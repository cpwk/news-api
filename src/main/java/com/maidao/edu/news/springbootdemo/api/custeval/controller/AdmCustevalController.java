package com.maidao.edu.news.springbootdemo.api.custeval.controller;

import com.maidao.edu.news.springbootdemo.api.custeval.model.Custeval;
import com.maidao.edu.news.springbootdemo.api.custeval.qo.CustevalQo;
import com.maidao.edu.news.springbootdemo.api.custeval.service.ICustevalService;
import com.maidao.edu.news.springbootdemo.common.controller.Action;
import com.maidao.edu.news.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.news.springbootdemo.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdmCustevalController
 * 类描述:用户评价接口
 **/

@Controller
@RequestMapping("/adm/custeval")
public class AdmCustevalController extends BaseController {

    @Autowired
    private ICustevalService custevalService;

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String custeval) throws Exception {
        custevalService.save(parseModel(custeval, new Custeval()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) throws Exception {
        custevalService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/custeval")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView article(Integer id) throws Exception {
        return feedback(custevalService.custeval(id));
    }

    @RequestMapping(value = "/custevals")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView articles(String custevalQo) throws Exception {
        return feedback(custevalService.custevals(parseModel(custevalQo, new CustevalQo()), true));
    }
}