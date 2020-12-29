package com.maidao.edu.news.springbootdemo.common.file.controller;

import com.maidao.edu.news.springbootdemo.common.controller.Action;
import com.maidao.edu.news.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.news.springbootdemo.common.controller.BaseController;
import com.maidao.edu.news.springbootdemo.common.file.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:OssController
 * 类描述:个人阿里云oss服务控制层接口
 **/
@Controller
@RequestMapping("/image")
public class OssController extends BaseController {

    @Autowired
    private OssService ossService;

    @RequestMapping(value = "/upload")
    @Action(session = ActionSession.NONE)
    public ModelAndView upload(MultipartFile file) throws Exception {
        return feedback(ossService.upload(file));
    }


}







