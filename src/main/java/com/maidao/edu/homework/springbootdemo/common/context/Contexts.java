package com.maidao.edu.homework.springbootdemo.common.context;

import com.maidao.edu.homework.springbootdemo.api.admin.model.AdminSessionWrap;
import com.maidao.edu.homework.springbootdemo.api.trainer.authority.TrainerSessionWrap;
import com.maidao.edu.homework.springbootdemo.api.user.model.UserSessionWrap;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import com.maidao.edu.homework.springbootdemo.common.resources.LocaleBundles;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:Contexts
 * 类描述:TODO
 **/

public class Contexts {


    public static void set(Context context) {
        SessionThreadLocal.getInstance().set(context);
    }

    public static Context get() {
        return SessionThreadLocal.getInstance().get();
    }

    public static SessionWrap getSession() {
        return get().getSession();
    }

    public static String ensureLocale() {
        Context context = get();
        if (context == null) {
            return LocaleBundles.getDefaultLocale();
        }
        return context.getLocale();
    }

    public static Integer sessionUserId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer id = null;
        if (wrap instanceof UserSessionWrap) {
            id = ((UserSessionWrap) wrap).getUser().getId();
        }
        return id;
    }

    public static Integer sessionAdminId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer id = null;
        if (wrap instanceof AdminSessionWrap) {
            id = ((AdminSessionWrap) wrap).getAdmin().getId();
        }
        return id;
    }

    public static Integer requestTrainerId() throws ServiceException {
        Context context = get();
        if (context == null) {
            throw new ServiceException(0);
        }
        Integer id = sessionTrainerId();
        if (id == null) {
            throw new ServiceException(0);
        }
        return id;
    }

    public static Integer sessionTrainerId() throws ServiceException {
        Context context = get();
        if (context == null) {
            return null;
        }
        SessionWrap wrap = context.getSession();
        Integer id = null;

        if (wrap instanceof TrainerSessionWrap) {
            id = ((TrainerSessionWrap) wrap).getTrainer().getId();
        }
        return id;
    }


}
