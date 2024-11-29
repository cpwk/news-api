package com.maidao.edu.news.common.exception;

import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ServiceException
 * 类描述:公用服务错误类型接口
 **/
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 6747739587143099754L;
    private int errorCode;
    private Object[] errorParams;
    private Map<?, ?> errorData;

    public ServiceException(int errorCode) {
        this.errorCode = errorCode;
    }

    public ServiceException(int errorCode, Object... errorParams) {
        super();
        this.errorCode = errorCode;
        this.errorParams = errorParams;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Object[] getErrorParams() {
        return errorParams;
    }

    public Map<?, ?> getErrorData() {
        return errorData;
    }

    public void setErrorData(Map<?, ?> errorData) {
        this.errorData = errorData;
    }

}
