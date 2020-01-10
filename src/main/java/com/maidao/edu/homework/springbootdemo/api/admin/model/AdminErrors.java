package com.maidao.edu.homework.springbootdemo.api.admin.model;

import com.maidao.edu.homework.springbootdemo.common.exception.ErrorCode;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdminErrors
 * 类描述:admin相关错误定义
 **/

public interface AdminErrors extends ErrorCode {

    public final static int ERR_ADMIN_MOBILE_INVALID = 1000;
    public final static int ERR_ADMIN_PASSWORD_FORMAT = 1001;
    public final static int ERR_ADMIN_NOTEXIST = 1002;
    public final static int ERR_ADMIN_PASSWORD_ERROR = 1003;
    public final static int ERR_ADMIN_UNREACHED_ERROR = 1004;
    public final static int ERR_ADMIN_USERNAME_USED = 1005;

}
