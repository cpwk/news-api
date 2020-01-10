package com.maidao.edu.homework.springbootdemo.api.job.controller;


import com.maidao.edu.homework.springbootdemo.api.job.entity.JobTypeVO;
import com.maidao.edu.homework.springbootdemo.api.job.model.Job;
import com.maidao.edu.homework.springbootdemo.api.job.qo.JobQo;
import com.maidao.edu.homework.springbootdemo.api.job.service.IJobService;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import com.maidao.edu.homework.springbootdemo.common.entity.KeyValue;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
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
 * 类名称:JobController
 * 类描述:控制层接口
 **/
@Controller
@RequestMapping("/adm/job")
public class JobController extends BaseController {

    @Autowired
    private IJobService jobService;

    private List<KeyValue> jobTypes = JobTypeVO.getTypes();

    @RequestMapping(value = "/jobTypes")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView jobTypes() {
        return feedback(jobTypes);
    }

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String job) throws Exception {
        jobService.save(parseModel(job, new Job()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) {
        jobService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/job")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView job(Integer id) {
        return feedback(jobService.job(id));
    }

    @RequestMapping(value = "/jobs")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView jobs(String jobQo) throws ServiceException {
        return feedback(jobService.jobs(parseModel(jobQo, new JobQo())));
    }

}