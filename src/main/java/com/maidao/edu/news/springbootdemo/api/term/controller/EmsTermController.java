package com.maidao.edu.news.springbootdemo.api.term.controller;

import com.maidao.edu.news.springbootdemo.api.term.service.ITermService;
import com.maidao.edu.news.springbootdemo.common.controller.Action;
import com.maidao.edu.news.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.news.springbootdemo.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Controller
@RequestMapping("/ems/term")
public class EmsTermController extends BaseController {

    @Autowired
    private ITermService termService;

    @RequestMapping(value = "/terms")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView terms() throws Exception {
        return feedback(termService.terms(false));
    }


}
