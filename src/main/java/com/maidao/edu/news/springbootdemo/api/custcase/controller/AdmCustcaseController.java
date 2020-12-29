package com.maidao.edu.news.springbootdemo.api.custcase.controller;

import com.maidao.edu.news.springbootdemo.api.custcase.model.Custcase;
import com.maidao.edu.news.springbootdemo.api.custcase.qo.CustcaseQo;
import com.maidao.edu.news.springbootdemo.api.custcase.service.ICustcaseService;
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
 * 类名称:AdmCustcaseController
 * 类描述:用户案例接口
 **/

@Controller
@RequestMapping("/adm/custcase")
public class AdmCustcaseController extends BaseController {

    @Autowired
    private ICustcaseService custcaseService;

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String custcase) throws Exception {
        custcaseService.save(parseModel(custcase, new Custcase()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) throws Exception {
        custcaseService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/custcase")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView custcase(Integer id) throws Exception {
        return feedback(custcaseService.custcase(id));
    }

    @RequestMapping(value = "/custcases")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView custcases(String custcaseQo) throws Exception {
        return feedback(custcaseService.custcases(parseModel(custcaseQo, new CustcaseQo()), true));
    }
}