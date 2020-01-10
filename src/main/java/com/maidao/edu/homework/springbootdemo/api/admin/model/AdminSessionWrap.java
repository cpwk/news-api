package com.maidao.edu.homework.springbootdemo.api.admin.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.maidao.edu.homework.springbootdemo.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdminSessionWrap
 * 类描述:将admin和adminsession糅合到一起返回
 **/

public class AdminSessionWrap implements SessionWrap {

    private Admin admin;
    @JSONField(serialize = false)//以json格式返回时不显示
    private AdminSession adminSession;

    public AdminSessionWrap(Admin admin, AdminSession adminSession) {
        this.admin = admin;
        this.adminSession = adminSession;
    }

    public AdminSession getAdminSession() {
        return adminSession;
    }

    public void setAdminSession(AdminSession adminSession) {
        this.adminSession = adminSession;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
