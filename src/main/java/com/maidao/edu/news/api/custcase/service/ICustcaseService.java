package com.maidao.edu.news.api.custcase.service;

import com.maidao.edu.news.api.custcase.model.Custcase;
import com.maidao.edu.news.api.custcase.qo.CustcaseQo;
import com.maidao.edu.news.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ICustcaseService
 * 类描述:用户案例服务层接口
 **/

public interface ICustcaseService {

    List<Custcase> getTopCases(int limit);

    Custcase custcase(int id);

    Page<Custcase> custcases(CustcaseQo qo, boolean adm) throws ServiceException;

    void save(Custcase custcase) throws ServiceException;

    void remove(int id);
}
