package com.maidao.edu.news.springbootdemo.api.trainingproject.controller;

import com.maidao.edu.news.springbootdemo.api.trainingproject.model.TrainingProject;
import com.maidao.edu.news.springbootdemo.api.trainingproject.qo.TrainingProjectQo;
import com.maidao.edu.news.springbootdemo.api.trainingproject.service.ITrainingProjectService;
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
@RequestMapping("/adm/trainingProject")
public class AdmTrainingProjectController extends BaseController {

    @Autowired
    private ITrainingProjectService trainingProjectService;

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String trainingProject) throws Exception {
        trainingProjectService.save(parseModel(trainingProject, new TrainingProject()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) {
        trainingProjectService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/trainingProject")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView trainingProject(Integer id) {
        return feedback(trainingProjectService.trainingProject(id));
    }

    @RequestMapping(value = "/trainingProjects")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView trainingProjects(String trainingProjectQo) throws Exception {
        return feedback(trainingProjectService.trainingProjects(parseModel(trainingProjectQo, new TrainingProjectQo()), true));
    }

}