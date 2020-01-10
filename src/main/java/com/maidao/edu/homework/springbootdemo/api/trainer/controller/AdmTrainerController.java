package com.maidao.edu.homework.springbootdemo.api.trainer.controller;


import com.maidao.edu.homework.springbootdemo.api.trainer.model.Trainer;
import com.maidao.edu.homework.springbootdemo.api.trainer.qo.TrainerQo;
import com.maidao.edu.homework.springbootdemo.api.trainer.qo.TrainerSessionQo;
import com.maidao.edu.homework.springbootdemo.api.trainer.service.ITrainerService;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
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
@RequestMapping("/adm/trainer")
public class AdmTrainerController extends BaseController {

    @Autowired
    private ITrainerService trainerService;

    @RequestMapping(value = "/save_trainer")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_trainer(String trainer) throws Exception {
        trainerService.save_trainer(parseModel(trainer, new Trainer()));
        return feedback(null);
    }

    @RequestMapping(value = "/trainers")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView trainers(String trainerQo) throws ServiceException {
        return feedback(trainerService.trainers(parseModel(trainerQo, new TrainerQo())));
    }

    @RequestMapping(value = "/trainer")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView trainer(Integer id) throws ServiceException {
        return feedback(trainerService.trainer(id, false));
    }

    @RequestMapping(value = "/trainerSessions")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView adminSessions(String trainerSessionQo) throws Exception {
        return feedback(trainerService.trainerSessions(parseModel(trainerSessionQo, new TrainerSessionQo())));
    }

    @RequestMapping(value = "/remove_trainer")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_trainer(Integer id) throws ServiceException {
        trainerService.remove_trainer(id, false);
        return feedback(null);
    }

    @RequestMapping(value = "/update_password")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView update_password(Integer id, String password) throws Exception {
        trainerService.update_password(id, password, false);
        return feedback(null);
    }


}