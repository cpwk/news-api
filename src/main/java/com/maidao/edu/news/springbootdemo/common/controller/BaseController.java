package com.maidao.edu.news.springbootdemo.common.controller;

import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.exception.ArgumentServiceException;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.news.springbootdemo.common.util.WebUtils;
import com.sunnysuperman.commons.bean.Bean;
import com.sunnysuperman.commons.bean.ParseBeanOptions;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:BaseController
 * 类描述:定义基础控制类,封装访问控制层公用方法
 **/

public class BaseController {

    public static final int PAGE_SIZE = 20;

    protected static <T> T parseModel(String modelJSON, T model) throws ServiceException {
        return parseModel(modelJSON, model, null, null);
    }

    protected static <T> T parseModel(String modelJSON, T model, String key) throws ServiceException {
        return parseModel(modelJSON, model, key, null);
    }

    protected static <T> T parseModel(String modelJSON, T model, String key, ParseBeanOptions options)
            throws ServiceException {
        try {
            return Bean.fromJson(modelJSON, model, options);
        } catch (Exception e) {
            throw new ArgumentServiceException((key != null ? key : "model"));
        }
    }

    protected static Integer parsePageNo(Integer pageNo) {
        if (pageNo == null || pageNo < 0) {
            return Constants.DEFAULT_PAGENUMBER;
        }
        return pageNo;
    }

    protected static Integer parsePageSize(Integer pageSize) {
        if (pageSize == null || pageSize < Constants.PAGESIZE_MIN) {
            return Constants.PAGESIZE_MIN;
        }
        return pageSize;
    }

    protected ModelAndView feedback() {
        return feedback(null);
    }

    protected ModelAndView feedback(Object obj) {
        Object result = obj != null ? obj : "success";
        Map<String, Object> data = new HashMap<>();
        data.put("errcode", 0);
        data.put("result", result);
        return new ModelAndView(new JsonView(data));
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ra.getRequest();
    }

    protected String getRemoteAddress() {
        return WebUtils.getRemoteAddress(getRequest());
    }
}