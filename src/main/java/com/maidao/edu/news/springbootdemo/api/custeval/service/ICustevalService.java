package com.maidao.edu.news.springbootdemo.api.custeval.service;

import com.maidao.edu.news.springbootdemo.api.custeval.model.Custeval;
import com.maidao.edu.news.springbootdemo.api.custeval.qo.CustevalQo;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ICustevalService
 * 类描述:用户评价服务层接口
 **/

public interface ICustevalService {

    List<Custeval> getTopCustevals(int limit);

    Custeval custeval(int id);

    Page<Custeval> custevals(CustevalQo qo, boolean adm);

    void save(Custeval custeval) throws ServiceException;

    void remove(int id);
}
