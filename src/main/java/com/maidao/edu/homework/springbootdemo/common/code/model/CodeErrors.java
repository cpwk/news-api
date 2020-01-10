package com.maidao.edu.homework.springbootdemo.common.code.model;

import com.maidao.edu.homework.springbootdemo.common.exception.ErrorCode;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:CodeErrors
 * 类描述:验证码相关错误类型定义
 **/

public interface CodeErrors extends ErrorCode {

    public static final int ERR_VCODE_INVALID = 100;
    public static final int ERR_VCODE_OVERTIME = 101;
    public static final int ERR_MOBILE_INVALID = 102;
    public static final int ERR_ALIYUN_EXCEPTION = 103;
    public static final int ERR_MOBILE_VCODE_OVERTIME = 104;

}
