package com.maidao.edu.homework.springbootdemo.api.admin.controller;

import com.maidao.edu.homework.springbootdemo.api.admin.authority.AdminPermissionVO;
import com.maidao.edu.homework.springbootdemo.api.admin.model.Admin;
import com.maidao.edu.homework.springbootdemo.api.admin.model.Role;
import com.maidao.edu.homework.springbootdemo.api.admin.qo.AdminSessionQo;
import com.maidao.edu.homework.springbootdemo.api.admin.service.AdminService;
import com.maidao.edu.homework.springbootdemo.common.context.Contexts;
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
 * 类名称:AdminController
 * 类描述:admin接口
 **/

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/sign_in")
    @Action(session = ActionSession.NONE)
    public ModelAndView sign_in(String admin) throws Exception {
        return feedback(adminService.signIn(parseModel(admin, new Admin()), getRemoteAddress()));
    }

    @Action(session = ActionSession.ADMIN)
    @RequestMapping(value = "/temp")
    public ModelAndView temp() {
        System.out.println(Contexts.sessionAdminId());
        return feedback();
    }

    @Action(session = ActionSession.ADMIN)
    @RequestMapping(value = "/find_alladmin")
    public ModelAndView find_alladmin() throws Exception {
        return feedback(adminService.findAllAdmin());
    }

    @RequestMapping(value = "/admin_sessions")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView admin_sessions(String adminSessionQo) throws Exception {
        return feedback(adminService.adminSessions(parseModel(adminSessionQo, new AdminSessionQo())));
    }

    @RequestMapping(value = "/find_byid")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView find_byid(Integer id) throws Exception {
        return feedback(adminService.findById(id));
    }

    @RequestMapping(value = "/remove_admin")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView removeAdmin(Integer id) throws Exception {
        adminService.removeAdmin(id);
        return feedback(null);
    }

    @RequestMapping(value = "/update_password")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView update_password(String password, String oldPassword) throws Exception {
        adminService.updatePassword(password, oldPassword);
        return feedback(null);
    }

    @RequestMapping(value = "/roles")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView roles() throws Exception {
        return feedback(adminService.roles(true));
    }

    @RequestMapping(value = "/admin")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView admin(Integer id) throws Exception {
        return feedback(adminService.admin(id, false));
    }

    @RequestMapping(value = "/save_admin")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save_admin(String admin) throws Exception {
        adminService.save_admin(parseModel(admin, new Admin()));
        return feedback(null);
    }

    @RequestMapping(value = "/remove_role")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView remove_role(Integer id) throws Exception {
        adminService.remove_role(id);
        return feedback(null);
    }

    @RequestMapping(value = "/permissions")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView permissions() throws Exception {
        return feedback(AdminPermissionVO.initPermissions());
    }

    @RequestMapping(value = "/role")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView role(Integer id) throws Exception {
        return feedback(adminService.role(id));
    }

    @RequestMapping(value = "/save_role")
    @Action(session = ActionSession.ADMIN)
    public ModelAndView save(String role) throws Exception {
        adminService.save_role(parseModel(role, new Role()));
        return feedback(null);
    }
}
