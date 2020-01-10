package com.maidao.edu.homework.springbootdemo.api.trainer.controller;


import com.maidao.edu.homework.springbootdemo.api.trainer.model.Trainer;
import com.maidao.edu.homework.springbootdemo.api.trainer.service.ITrainerService;
import com.maidao.edu.homework.springbootdemo.common.authority.AdminType;
import com.maidao.edu.homework.springbootdemo.common.authority.RequiredPermission;
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
@RequestMapping("/trainer/trainer")
public class TrainerTrainerController extends BaseController {

    @Autowired
    private ITrainerService trainerService;

    @Autowired
    private ICommonService commonService;

    @RequestMapping(value = "/signin")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView signin(String trainer, String valCode) throws Exception {
        return feedback(trainerService.signin(parseModel(trainer, new Trainer()), parseModel(valCode, new ValCode()), getRemoteAddress()));
    }

    @RequestMapping(value = "/profile")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView profile() throws Exception {
        return feedback(trainerService.profile());
    }

    @RequestMapping(value = "/update_my_password")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView update_my_password(String password, String oldPassword) throws Exception {
        trainerService.update_my_password(password, oldPassword);
        return feedback(null);
    }

    @RequestMapping(value = "/reset_my_password")
    @RequiredPermission(adminType = AdminType.NONE)
    public ModelAndView reset_my_password(String valCode, String password) throws Exception {
        trainerService.reset_my_password(parseModel(valCode, new ValCode()), password);
        return feedback(null);
    }
}