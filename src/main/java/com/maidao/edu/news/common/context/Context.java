package com.maidao.edu.news.common.context;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Context
 * 类描述:定义会话环境属性
 **/

public class Context {

    private String locale;
    private SessionWrap session;
    private String requestIp;
    private Long customerId;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public SessionWrap getSession() {
        return session;
    }

    public void setSession(SessionWrap session) {
        this.session = session;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
