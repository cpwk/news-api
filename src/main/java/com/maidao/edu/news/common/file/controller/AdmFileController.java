package com.maidao.edu.news.common.file.controller;

import com.maidao.edu.news.common.controller.Action;
import com.maidao.edu.news.common.controller.ActionSession;
import com.maidao.edu.news.common.controller.BaseController;
import com.maidao.edu.news.common.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdmFileController
 * 类描述:文件上传控制层接口
 **/
@Controller
@RequestMapping("/adm/file")
public class AdmFileController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload_token")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView upload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("user-head-photo", fileName, fileSize, true));
    }
}