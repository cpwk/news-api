package com.maidao.edu.homework.springbootdemo.api.trainee.controller;

import com.maidao.edu.homework.springbootdemo.api.heavywork.service.IHeavyWorkService;
import com.maidao.edu.homework.springbootdemo.api.trainee.model.Trainee;
import com.maidao.edu.homework.springbootdemo.api.trainee.qo.TraineeQo;
import com.maidao.edu.homework.springbootdemo.api.trainee.qo.TraineeSessionQo;
import com.maidao.edu.homework.springbootdemo.api.trainee.service.ITraineeService;
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
@RequestMapping("/adm/trainee")
public class AdmTraineeController extends BaseController {

    @Autowired
    private ITraineeService traineeService;

    @Autowired
    private IHeavyWorkService heavyWorkService;

    @RequestMapping(value = "/save_trainee")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_trainee(String trainee) throws Exception {
        traineeService.save_trainee(parseModel(trainee, new Trainee()));
        return feedback(null);
    }

    @RequestMapping(value = "/trainees")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView trainees(String traineeQo) throws ServiceException {
        return feedback(traineeService.trainees(parseModel(traineeQo, new TraineeQo())));
    }

    @RequestMapping(value = "/export_trainees")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView export_trainees(String traineeQo) throws Exception {
        return feedback(traineeService.exportTrainees(parseModel(traineeQo, new TraineeQo())));
    }

    @RequestMapping(value = "/export_trainees_progress")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView export_trainees_progress(Integer id, String secret) throws Exception {
        return feedback(heavyWorkService.getById(id, secret));
    }

    @RequestMapping(value = "/trainee")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView trainee(Integer id) throws ServiceException {
        return feedback(traineeService.trainee(id, false));
    }

    @RequestMapping(value = "/traineeSessions")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView adminSessions(String traineeSessionQo) throws Exception {
        return feedback(traineeService.traineeSessions(parseModel(traineeSessionQo, new TraineeSessionQo())));
    }

    @RequestMapping(value = "/remove_trainee")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_trainee(Integer id) throws ServiceException {
        traineeService.remove_trainee(id, false);
        return feedback(null);
    }

    @RequestMapping(value = "/update_password")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView update_password(Integer id, String password) throws Exception {
        traineeService.update_password(id, password, false);
        return feedback(null);
    }
}