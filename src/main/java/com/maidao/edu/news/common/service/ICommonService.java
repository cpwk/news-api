package com.maidao.edu.news.common.service;


import com.maidao.edu.news.common.entity.ValCode;
import com.maidao.edu.news.common.exception.ServiceException;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ICommonService {

    void saveValCode(Long key, ValCode valCode);

    ValCode getValCode(Long key) throws ServiceException;

    void reset_password(ValCode valCode, String password, String salt) throws ServiceException;

}
