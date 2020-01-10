package com.maidao.edu.homework.springbootdemo.api.trainee.authority;

import com.maidao.edu.homework.springbootdemo.common.context.Context;
import com.maidao.edu.homework.springbootdemo.common.context.Contexts;
import com.maidao.edu.homework.springbootdemo.common.context.SessionWrap;
import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TraineeContext {

    public static TraineeSessionWrap getSessionWrap() {
        Context context = Contexts.get();
        if (context == null) {
            return null;
        }
        SessionWrap session = context.getSession();
        if (session == null) {
            return null;
        }
        if (!(session instanceof TraineeSessionWrap)) {
            return null;
        }
        return (TraineeSessionWrap) session;
    }

    public static TraineeSessionWrap requireSessionWrap() throws ServiceException {
        TraineeSessionWrap session = getSessionWrap();
        if (session == null) {
            throw new ServiceException(0);
        }
        return session;
    }
}