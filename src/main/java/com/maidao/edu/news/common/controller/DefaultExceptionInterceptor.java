package com.maidao.edu.news.common.controller;

import com.maidao.edu.news.common.context.Contexts;
import com.maidao.edu.news.common.exception.RuntimeServiceException;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.resources.LocaleBundles;
import com.maidao.edu.news.common.util.L;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:DefaultExceptionInterceptor
 * 类描述:默认错误拦截器
 **/
@ControllerAdvice
public class DefaultExceptionInterceptor {

    private static final RuntimeServiceException DEFAULT_EXCEPTION = new RuntimeServiceException();

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest request, HandlerMethod handlerMethod, Throwable ex) {
//        L.error(ex);
        if (!(ex instanceof ServiceException)) {
            ApiLog.log(request, null);
        }
        Action action = handlerMethod.getMethodAnnotation(Action.class);
        ActionResultType resultType = action == null ? ActionResultType.Json : action.result();
        if (resultType == ActionResultType.Json) {
            ServiceException se;
            if (ex instanceof ServiceException) {
                se = (ServiceException) ex;
            } else {
                L.error(ex);
                se = DEFAULT_EXCEPTION;
            }
            int errorCode = se.getErrorCode();
            String errorMsg = LocaleBundles.getWithArrayParams(Contexts.ensureLocale(), "err." + errorCode,
                    se.getErrorParams());
            Map<String, Object> result = new HashMap<>();
            result.put("errcode", errorCode);
            result.put("errmsg", errorMsg);
            result.put("errdata", se.getErrorData());
            return new ModelAndView(new JsonView(result));
        } else {
            return null;
        }
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Throwable ex) {
        return new ModelAndView(new NotFoundView());
    }
}