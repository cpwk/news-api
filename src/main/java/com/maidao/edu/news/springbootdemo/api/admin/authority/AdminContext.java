package com.maidao.edu.news.springbootdemo.api.admin.authority;

import com.maidao.edu.news.springbootdemo.api.admin.model.AdminSessionWrap;
import com.maidao.edu.news.springbootdemo.common.context.Context;
import com.maidao.edu.news.springbootdemo.common.context.Contexts;
import com.maidao.edu.news.springbootdemo.common.context.SessionWrap;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdminContext
 * 类描述:权限
 **/

public class AdminContext {
    public static AdminSessionWrap getSessionWrap() {
        Context context = Contexts.get();
        if (context == null) {
            return null;
        }
        SessionWrap session = context.getSession();
        if (session == null) {
            return null;
        }
        if (!(session instanceof AdminSessionWrap)) {
            return null;
        }
        return (AdminSessionWrap) session;
    }
}
