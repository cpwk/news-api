package com.maidao.edu.news.springbootdemo.common.entity;

public class Constants {
    public static final int DEFAULT_PAGENUMBER = 0;
    public static final int PAGESIZE_MIN = 10;
    public static final int PAGESIZE_MAX = 50;
    public static final byte STATUS_OK = 1;// 默认
    public static final int ISTATUS_OK = 1;// 默认
    public static final byte STATUS_HALT = 2;// 删除、停用、取消
    public static final int ISTATUS_HALT = 2;// 删除、停用、取消
    public static int CACHE_REDIS_EXPIRE = 3600 * 48;
    public static int PAGESIZE_MED = 20;
    public static int PAGESIZE_INF = 10000;
    public static int SESSION_EXPIRE_DAYS = 2;
    // 权限操作级别
    public static String LEVEL_PRIMARY = "blue";
    public static String LEVEL_IMPORTANT = "red";
    public static String LEVEL_WARNING = "orange";
    //文件
    public static int EXPIRE_UPLOAD = 60 * 60;
    public static int MAX_UPLOAD_SIZE = 200 * 1024 * 1024;
}