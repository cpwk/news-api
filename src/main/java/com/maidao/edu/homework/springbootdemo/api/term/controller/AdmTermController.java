package com.maidao.edu.homework.springbootdemo.api.term.controller;

import com.maidao.edu.homework.springbootdemo.api.term.model.Term;
import com.maidao.edu.homework.springbootdemo.api.term.service.ITermService;
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
 * 类名称:TODO
 * 类描述:TODO
 **/
@Controller
@RequestMapping("/adm/term")
public class AdmTermController extends BaseController {

    @Autowired
    private ITermService termService;

    @RequestMapping(value = "/save_term")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_term(String term) throws Exception {
        termService.save_term(parseModel(term, new Term()));
        return feedback(null);
    }

    @RequestMapping(value = "/set_term_default")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView set_term_default(Integer id) throws Exception {
        termService.set_term_default(id);
        return feedback(null);
    }

    @RequestMapping(value = "/update_status")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView update_status(Integer id, Byte status) throws Exception {
        termService.update_status(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/terms")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView terms() {
        return feedback(termService.terms(true));
    }

    @RequestMapping(value = "/term")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView term(Integer id) {
        return feedback(termService.term(id));
    }


}
