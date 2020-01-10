package com.maidao.edu.homework.springbootdemo.api.trainee.controller;


import com.maidao.edu.homework.springbootdemo.api.trainee.model.Trainee;
import com.maidao.edu.homework.springbootdemo.api.trainee.service.ITraineeService;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import com.maidao.edu.homework.springbootdemo.common.entity.ValCode;
import com.maidao.edu.homework.springbootdemo.common.service.ICommonService;
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
@RequestMapping("/trainee/trainee")
public class TraineeTraineeController extends BaseController {

    @Autowired
    private ITraineeService traineeService;

    @Autowired
    private ICommonService commonService;

    @RequestMapping(value = "/signin")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView signin(String trainee, String valCode) throws Exception {
        return feedback(traineeService.signin(parseModel(trainee, new Trainee()), parseModel(valCode, new ValCode()), getRemoteAddress()));
    }

    @RequestMapping(value = "/profile")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView profile() throws Exception {
        return feedback(traineeService.profile());
    }

    @RequestMapping(value = "/update_my_password")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView update_my_password(String password, String oldPassword) throws Exception {
        traineeService.update_my_password(password, oldPassword);
        return feedback(null);
    }

    @RequestMapping(value = "/reset_my_password")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView reset_my_password(String valCode, String password) throws Exception {
        traineeService.reset_my_password(parseModel(valCode, new ValCode()), password);
        return feedback(null);
    }


}