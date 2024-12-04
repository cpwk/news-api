package com.maidao.edu.news.common.controller;

import com.maidao.edu.news.common.entity.ApiParams;
import com.maidao.edu.news.common.util.WebUtils;
import com.maidao.edu.news.common.authority.RequiredPermission;
import com.maidao.edu.news.common.authority.SessionUtil;
import com.maidao.edu.news.common.context.Context;
import com.maidao.edu.news.common.context.Contexts;
import com.maidao.edu.news.common.context.SessionThreadLocal;
import com.maidao.edu.news.common.context.SessionWrap;
import com.maidao.edu.news.common.exception.ArgumentServiceException;
import com.sunnysuperman.commons.util.FormatUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:DefaultInterceptor
 * 类描述:会话请求拦截器
 **/

@ControllerAdvice
public class DefaultInterceptor extends SessionUtil implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (CrossDomainHandler.handle(request, response)) {
            return false;
        }

        Context wrapper = new Context();
        wrapper.setRequestIp(WebUtils.getRemoteAddress(request));
        SessionThreadLocal.getInstance().set(wrapper);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Action action = handlerMethod.getMethodAnnotation(Action.class);
        wrapper.setCustomerId(FormatUtil.parseLong(WebUtils.getHeader(request, ApiParams.CUSTOMER_ID)));

        boolean authorized = false;
        RequiredPermission requiredPermission = handlerMethod.getMethodAnnotation(RequiredPermission.class);

        for (ActionSession session : action.session())
            if (session == ActionSession.ADMIN) {
                String token = WebUtils.getHeader(request, "admin-token");
                authorized = checkAdminToken(token);
            } else if (session == ActionSession.USER) {
                String token = WebUtils.getHeader(request, "admin-token");
                authorized = checkUserToken(token);
            } else if (session == ActionSession.NONE) {
                authorized = true;
            }
        if (!authorized) {
            throw new ArgumentServiceException("token expired");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private SessionWrap findSessionWrap(Enum type, HttpServletRequest request, String permission) throws Exception {
        String token = WebUtils.getHeader(request, ApiParams.ADMIN_TOKEN);
        SessionWrap wrap = adminPermissionCheck(type, token, permission);
        Contexts.get().setSession(wrap);
        return wrap;
    }
}