package com.maidao.edu.news.api.news.service;

import com.maidao.edu.news.api.news.model.News;
import com.maidao.edu.news.api.news.qo.NewsQo;
import org.springframework.data.domain.Page;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-30 09:54
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:NewsService
 * 类描述:服务层接口，会调用业务逻辑层的一个方法，来实现该接口的具体业务逻辑和功能，业务逻辑层需要编写接口。
 **/

public interface NewsService {

    News findById(Integer id) throws Exception;

    void deleteById(Integer id) throws Exception;

    void save(News news) throws Exception;

    Page<News> findAll(NewsQo newsQo) throws Exception;

    void deleteAll() throws Exception;
}