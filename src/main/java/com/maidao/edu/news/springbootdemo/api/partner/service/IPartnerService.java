package com.maidao.edu.news.springbootdemo.api.partner.service;


import com.maidao.edu.news.springbootdemo.api.partner.model.Partner;
import com.maidao.edu.news.springbootdemo.api.partner.qo.PartnerQo;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IPartnerService
 * 类描述:业务逻辑接口
 **/
public interface IPartnerService {

    List<Partner> partners(PartnerQo qo, boolean admin);

    Partner partner(int id);

    void save(Partner partner) throws ServiceException;

    void remove(int id);
}
