package com.maidao.edu.news.api.user.model;

import com.maidao.edu.news.common.exception.ErrorCode;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:UserErrors
 * 类描述:用户错误类型接口，与zh_CN.ini中的错误类型相对应
 **/

public interface UserErrors extends ErrorCode {

    int ERR_USER_MOBILE_INVALID = 10000;
    int ERR_USER_EMAIE_INVALID = 10001;
    int ERR_USER_NICK_FORMAT = 10002;
    int ERR_USER_PASSWORD_FORMAT = 10003;
    int ERR_USER_MOBILE_USED = 10004;
    int ERR_USER_EMAIE_USED = 10005;
    int ERR_USER_NOTEXIST = 10006;
    int ERR_USER_PASSWORD_ERROR = 10007;
    int ERR_USER_MOBILE_DIFFER = 10008;
    int ERR_MOBILECODE_NONE = 1011;

}
