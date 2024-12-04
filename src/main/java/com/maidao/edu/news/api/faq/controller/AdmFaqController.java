package com.maidao.edu.news.api.faq.controller;


import com.maidao.edu.news.api.faq.model.Faq;
import com.maidao.edu.news.api.faq.service.IFaqService;
import com.maidao.edu.news.common.controller.Action;
import com.maidao.edu.news.common.controller.ActionSession;
import com.maidao.edu.news.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdmFaqController
 * 类描述:AdmFaq控制层接口
 **/
@Controller
@RequestMapping("/adm/faq")
public class AdmFaqController extends BaseController {

    @Autowired
    private IFaqService faqService;

    @RequestMapping(value = "/save_faq")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_faq(String faq) throws Exception {
        faqService.save_faq(parseModel(faq, new Faq()));
        return feedback(null);
    }

    @RequestMapping(value = "/faqs")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView faqs() {
        return feedback(faqService.faqs(true));
    }

    @RequestMapping(value = "/faq")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView faq(Integer id) {
        return feedback(faqService.faq(id));
    }

    @RequestMapping(value = "/remove_faq")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_faq(Integer id) {
        faqService.remove_faq(id);
        return feedback(null);
    }
}