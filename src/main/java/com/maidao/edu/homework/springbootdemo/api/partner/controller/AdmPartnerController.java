package com.maidao.edu.homework.springbootdemo.api.partner.controller;

import com.maidao.edu.homework.springbootdemo.api.partner.model.Partner;
import com.maidao.edu.homework.springbootdemo.api.partner.qo.PartnerQo;
import com.maidao.edu.homework.springbootdemo.api.partner.service.IPartnerService;
import com.maidao.edu.homework.springbootdemo.common.controller.Action;
import com.maidao.edu.homework.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.homework.springbootdemo.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdmPartnerController
 * 类描述:控制层接口
 **/
@Controller
@RequestMapping("/adm/partner")
public class AdmPartnerController extends BaseController {

    @Autowired
    private IPartnerService partnerService;

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String partner) throws Exception {
        partnerService.save(parseModel(partner, new Partner()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) throws Exception {
        partnerService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/partner")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView partner(Integer id) throws Exception {
        return feedback(partnerService.partner(id));
    }

    @RequestMapping(value = "/partners")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView partners(String partnerQo) throws Exception {
        return feedback(partnerService.partners(parseModel(partnerQo, new PartnerQo()), true));
    }

}
