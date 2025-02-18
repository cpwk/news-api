package com.maidao.edu.news.api.banner.controller;

import com.maidao.edu.news.api.banner.model.Banner;
import com.maidao.edu.news.api.banner.qo.BannerQo;
import com.maidao.edu.news.api.banner.service.IBannerService;
import com.maidao.edu.news.common.controller.Action;
import com.maidao.edu.news.common.controller.ActionSession;
import com.maidao.edu.news.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdmBannerController
 * 类描述:banner控制层接口
 **/

@Controller
@RequestMapping("/adm/banner")
public class AdmBannerController extends BaseController {

    @Autowired
    private IBannerService bannerService;

    @RequestMapping(value = "/save")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String banner) throws Exception {
        bannerService.save(parseModel(banner, new Banner()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove(Integer id) throws Exception {
        bannerService.remove(id);
        return feedback(null);
    }

    @RequestMapping(value = "/banner")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView banner(Integer id) throws Exception {
        return feedback(bannerService.banner(id));
    }

    @RequestMapping(value = "/banners")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView banners(String bannerQo) throws Exception {
        return feedback(bannerService.banners(parseModel(bannerQo, new BannerQo()), true));
    }
}