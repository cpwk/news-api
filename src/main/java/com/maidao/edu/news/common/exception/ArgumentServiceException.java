package com.maidao.edu.news.common.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ArgumentServiceException
 * 类描述:公用内容提示类接口
 **/

public class ArgumentServiceException extends ServiceException {

    private static final long serialVersionUID = -7398899188917026294L;

    public ArgumentServiceException(String key, Serializable value) {
        super(ErrorCode.ERR_ILLEGAL_ARGUMENT);
        Map<String, Object> errorData = new HashMap<String, Object>(2);
        errorData.put("key", key);
        errorData.put("value", value);
        this.setErrorData(errorData);
    }

    public ArgumentServiceException(String key) {
        this(key, null);
    }
}