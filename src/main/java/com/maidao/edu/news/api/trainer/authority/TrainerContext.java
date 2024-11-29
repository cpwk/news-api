package com.maidao.edu.news.api.trainer.authority;


import com.maidao.edu.news.common.context.Context;
import com.maidao.edu.news.common.context.Contexts;
import com.maidao.edu.news.common.context.SessionWrap;
import com.maidao.edu.news.common.exception.ServiceException;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class TrainerContext {

    public static TrainerSessionWrap getSessionWrap() {
        Context context = Contexts.get();
        if (context == null) {
            return null;
        }
        SessionWrap session = context.getSession();
        if (session == null) {
            return null;
        }
        if (!(session instanceof TrainerSessionWrap)) {
            return null;
        }
        return (TrainerSessionWrap) session;
    }

    public static TrainerSessionWrap requireSessionWrap() throws ServiceException {
        TrainerSessionWrap session = getSessionWrap();
        if (session == null) {
            throw new ServiceException(0);
        }
        return session;
    }


}
