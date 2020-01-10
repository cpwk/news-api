package com.maidao.edu.homework.springbootdemo.common.util;

import com.maidao.edu.homework.springbootdemo.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:L
 * 类描述:TODO
 **/
public class L {
    private static final Logger LOG = LoggerFactory.getLogger(L.class);
    private static final boolean INFO_ENABLED = LOG.isInfoEnabled();

    public static boolean isInfoEnabled() {
        return INFO_ENABLED;
    }

    public static void info(String msg) {
        LOG.info(msg);
    }

    public static void warn(String msg) {
        LOG.warn(msg);
    }

    public static void error(String msg) {
        LOG.error(msg);
    }

    public static void error(Throwable ex) {
        error(null, ex);
    }

    public static void error(String msg, Throwable ex) {
        if (ex == null) {
            if (msg != null) {
                LOG.error(msg);
            }
            return;
        }
        if (ex instanceof ServiceException) {
            ServiceException se = (ServiceException) ex;
            Throwable cause = se.getCause();
            if (cause != null) {
                error(msg, cause);
            }
        } else {
            LOG.error(msg, ex);
        }
    }

}
