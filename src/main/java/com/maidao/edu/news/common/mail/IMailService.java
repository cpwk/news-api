package com.maidao.edu.news.common.mail;


import com.maidao.edu.news.common.entity.ValCode;
import com.maidao.edu.news.common.exception.ServiceException;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IMailService
 * 类描述:TODO
 **/
public interface IMailService {

    ValCode getValCode(Long key) throws ServiceException;

    void sendEmail(long key, String email) throws Exception;
}