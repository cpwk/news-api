package com.maidao.edu.news.api.qa.controller;

import com.maidao.edu.news.api.qa.model.QATemplate;
import com.maidao.edu.news.api.qa.qo.QAPaperQo;
import com.maidao.edu.news.common.controller.BaseController;
import com.maidao.edu.news.api.heavywork.service.IHeavyWorkService;
import com.maidao.edu.news.api.qa.service.IQAService;
import com.maidao.edu.news.common.controller.Action;
import com.maidao.edu.news.common.controller.ActionSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdmQAController
 * 类描述:控制层接口
 **/
@Controller
@RequestMapping("/adm/qa")
public class AdmQAController extends BaseController {

    @Autowired
    private IQAService qaService;

    @Autowired
    private IHeavyWorkService heavyWorkService;

    @RequestMapping(value = "/save_qaTemplate")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_qaTemplate(String qaTemplate) throws Exception {
        qaService.saveQATemplate(parseModel(qaTemplate, new QATemplate()));
        return feedback(null);
    }

    @RequestMapping(value = "/status_qaTemplate")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView status_qaTemplate(Integer id, Byte status) throws Exception {
        qaService.statusQATemplate(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/qaTemplates")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView qaTemplates() {
        return feedback(qaService.qaTemplates());
    }

    @RequestMapping(value = "/qaTemplate")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView qaTemplate(Integer id) {
        return feedback(qaService.qaTemplate(id));
    }

    @RequestMapping(value = "/qaPapers")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView qaPapers(String qaPaperQo) throws Exception {
        return feedback(qaService.qaPapers(parseModel(qaPaperQo, new QAPaperQo())));
    }

    @RequestMapping(value = "/export_qaPapers")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView export_qaPapers(String qaPaperQo) throws Exception {
        return feedback(qaService.exportQAPapers(parseModel(qaPaperQo, new QAPaperQo())));
    }

    @RequestMapping(value = "/export_qaPapers_progress")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView export_qaPapers_progress(Integer id, String secret) throws Exception {
        return feedback(heavyWorkService.getById(id, secret));
    }

    @RequestMapping(value = "/status_qaPaper")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView status_qaPaper(Integer id, Byte status) throws Exception {
        qaService.statusQAPaper(id, status);
        return feedback(null);
    }

    @RequestMapping(value = "/remove_qaPaper")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_qaPaper(Integer id) throws Exception {
        qaService.removeQAPaper(id);
        return feedback(null);
    }
}
