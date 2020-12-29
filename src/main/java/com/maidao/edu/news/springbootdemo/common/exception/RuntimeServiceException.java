package com.maidao.edu.news.springbootdemo.common.exception;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:RuntimeServiceException
 * 类描述:公用运行错误类型接口
 **/
public class RuntimeServiceException extends ServiceException {
    private static final long serialVersionUID = 550893169395750576L;

    public RuntimeServiceException() {
        super(ErrorCode.ERR_UNKNOWN_ERROR);
    }

    public RuntimeServiceException(Throwable t) {
        this();
        this.initCause(t);
    }

    public RuntimeServiceException(String s) {
        this();
        this.initCause(new RuntimeException(s));
    }

}
