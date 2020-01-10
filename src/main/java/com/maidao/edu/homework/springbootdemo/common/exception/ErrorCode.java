package com.maidao.edu.homework.springbootdemo.common.exception;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:commonErrorCode
 * 类描述:公用错误类型接口
 **/
public interface ErrorCode {
    public static final int ERR_UNKNOWN_ERROR = 1;
    public static final int ERR_ILLEGAL_ARGUMENT = 2;
    public static final int ERR_PERMISSION_DENIED = 3;
    public static final int ERR_DETAILED_MESSAGE = 4;
    public static final int ERR_SESSION_EXPIRES = 5;
    public static final int ERR_OPERATION_TOO_FREQUENT = 6;
    public static final int ERR_DATA_NOT_FOUND = 7;
    public static final int ERR_NEED_UPGRADE = 8;
    public static final int ERR_CONTENT_EMPTY = 9;
    public static final int NO_PERMISSION = 10;
    public static final int SESSIONTIMEOUT = 11;
    public static final int ERROR_COVER_EMPTY = 12;
    public static final int ERR_FILE_NONE = 100000;
    public static final int ERR_FILE_THAN = 100001;
    public static final int ERR_FILE_THANMAX = 100002;


}