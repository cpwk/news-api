package com.maidao.edu.news.common.file.controller;

import com.maidao.edu.news.common.controller.Action;
import com.maidao.edu.news.common.controller.ActionSession;
import com.maidao.edu.news.common.controller.BaseController;
import com.maidao.edu.news.common.file.service.FileService;
import com.maidao.edu.news.common.file.service.ImgBase64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:EmsFileController
 * 类描述:图片上传控制层接口
 **/
@Controller
@RequestMapping("/ems/file")
public class EmsFileController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload_token")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView upload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("user-head-photo", fileName, fileSize, true));
    }

    @RequestMapping(value = "/img_to_base64")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView img_to_base64(String url) throws Exception {
        return feedback(ImgBase64Utils.base64FromURL(url));
    }
}