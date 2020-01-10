package com.maidao.edu.homework.springbootdemo.api.practice.controller;


import com.alibaba.fastjson.JSON;
import com.maidao.edu.homework.springbootdemo.api.practice.entity.PracticeItemTypeVO;
import com.maidao.edu.homework.springbootdemo.api.practice.model.Practice;
import com.maidao.edu.homework.springbootdemo.api.practice.qo.PracticeQo;
import com.maidao.edu.homework.springbootdemo.api.practice.qo.PracticeWo;
import com.maidao.edu.homework.springbootdemo.api.practice.service.IPracticeService;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import com.maidao.edu.homework.springbootdemo.common.entity.KeyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TrainerPracticeController
 * 类描述:控制层接口
 **/
@Controller
@RequestMapping("/trainer/practice")
public class TrainerPracticeController extends BaseController {

    @Autowired
    private IPracticeService practiceService;

    private List<KeyValue> formTypes = PracticeItemTypeVO.getTypes();

    @RequestMapping(value = "/formTypes")
    @Action(session = ActionSession.NONE)
    public ModelAndView formTypes() {
        return feedback(formTypes);
    }

    @RequestMapping(value = "/save_practices")
    @Action(session = ActionSession.NONE)
    public ModelAndView save_practices(String practices) throws Exception {
        practiceService.save_practices(JSON.parseArray(practices, Practice.class));
        return feedback(null);
    }

    @RequestMapping(value = "/remove_practice")
    @Action(session = ActionSession.NONE)
    public ModelAndView remove_practice(Integer id) throws Exception {
        practiceService.remove_practice(id);
        return feedback(null);
    }

    @RequestMapping(value = "/practices")
    @Action(session = ActionSession.NONE)
    public ModelAndView practices(String practiceQo) throws Exception {
        return feedback(practiceService.practices(parseModel(practiceQo, new PracticeQo()), PracticeWo.getNoneInstance()));
    }

}
