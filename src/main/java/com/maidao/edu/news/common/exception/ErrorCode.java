package com.maidao.edu.news.common.exception;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:commonErrorCode
 * 类描述:公用错误类型接口
 **/
public interface ErrorCode {
    int ERR_UNKNOWN_ERROR = 1;
    int ERR_ILLEGAL_ARGUMENT = 2;
    int ERR_PERMISSION_DENIED = 3;
    int ERR_DETAILED_MESSAGE = 4;
    int ERR_SESSION_EXPIRES = 5;
    int ERR_OPERATION_TOO_FREQUENT = 6;
    int ERR_DATA_NOT_FOUND = 7;
    int ERR_NEED_UPGRADE = 8;
    int ERR_CONTENT_EMPTY = 9;
    int NO_PERMISSION = 10;
    int SESSIONTIMEOUT = 11;
    int ERROR_COVER_EMPTY = 12;
    int ERR_FILE_NONE = 100000;
    int ERR_FILE_THAN = 100001;
    int ERR_FILE_THANMAX = 100002;


}