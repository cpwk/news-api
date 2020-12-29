package com.maidao.edu.news.springbootdemo.api.user.controller;

import com.maidao.edu.news.springbootdemo.api.user.model.User;
import com.maidao.edu.news.springbootdemo.api.user.qo.UserQo;
import com.maidao.edu.news.springbootdemo.api.user.qo.UserSessionQo;
import com.maidao.edu.news.springbootdemo.api.user.service.UserService;
import com.maidao.edu.news.springbootdemo.common.code.model.VCode;
import com.maidao.edu.news.springbootdemo.common.context.Contexts;
import com.maidao.edu.news.springbootdemo.common.controller.Action;
import com.maidao.edu.news.springbootdemo.common.controller.ActionSession;
import com.maidao.edu.news.springbootdemo.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:UserController
 * 类描述:用户接口
 **/

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Action(session = ActionSession.NONE)
    @RequestMapping(value = "sign_up")
    public ModelAndView sign_up(String user, String key, String smsCode) throws Exception {
        User model = parseModel(user, new User());
        userService.signUp(model, key, smsCode);
        return feedback();
    }

    @Action(session = ActionSession.NONE)
    @RequestMapping(value = "sign_in")
    public ModelAndView sign_in(String unknown, String password, String vCode) throws Exception {
        return feedback(userService.signIn(unknown, password, parseModel(vCode, new VCode()), getRemoteAddress()));
    }

    @Action(session = ActionSession.USER)
    @RequestMapping(value = "/temp")
    public ModelAndView temp() {
        System.out.println(Contexts.sessionUserId());
        return feedback();
    }

    @Action(session = ActionSession.NONE)
    @RequestMapping(value = "/forget_password")
    public ModelAndView forget_password(String mobile, String vCode) throws Exception {
        return feedback(userService.forgetPassword(mobile, parseModel(vCode, new VCode())));
    }

    @Action(session = ActionSession.NONE)
    @RequestMapping(value = "/reset_password")
    public ModelAndView reset_password(String mobile, String password, String key, String smsCode) throws Exception {
        userService.resetPassword(mobile, password, key, smsCode);
        return feedback();
    }

    @Action(session = ActionSession.USER)
    @RequestMapping(value = "/update_password")
    public ModelAndView update_password(String mobile, String password, String newpassword, String vCode) throws Exception {
        userService.updatePassword(mobile, password, newpassword, parseModel(vCode, new VCode()));
        return feedback();
    }

    @Action(session = ActionSession.ADMIN)
    @RequestMapping(value = "/find_alluser")
    public ModelAndView find_AllUser(String userQo) throws Exception {
        return feedback(userService.findAllUser(parseModel(userQo, new UserQo())));
    }

    @RequestMapping(value = "delete_byid")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView delete_byid(Integer id) throws Exception {
        System.out.println(id);
        userService.deleteById(id);
        return feedback();
    }

    @RequestMapping(value = "find_byid")
    @Action(session = ActionSession.NONE)
    public ModelAndView find_byid(Integer id) throws Exception {
        return feedback(userService.findById(id));
    }

    @RequestMapping(value = "/user_sessions")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView user_sessions(String userSessionQo) throws Exception {
        return feedback(userService.userSessions(parseModel(userSessionQo, new UserSessionQo())));
    }
}
