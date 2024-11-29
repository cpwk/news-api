package com.maidao.edu.news.common.context;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:SessionThreadLocal
 * 类描述:TODO
 **/

public class SessionThreadLocal extends ThreadLocal<Context> {

    private static final SessionThreadLocal INSTANCE = new SessionThreadLocal();

    private SessionThreadLocal() {
    }

    public static SessionThreadLocal getInstance() {
        return INSTANCE;
    }
}