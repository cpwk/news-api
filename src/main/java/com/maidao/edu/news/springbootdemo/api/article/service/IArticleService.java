package com.maidao.edu.news.springbootdemo.api.article.service;


import com.maidao.edu.news.springbootdemo.api.article.model.Article;
import com.maidao.edu.news.springbootdemo.api.article.qo.ArticleQo;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IArticleService
 * 类描述:Article业务逻辑层接口
 **/
public interface IArticleService {

    void addVisit(int id);

    List<Article> getTopArticles(int limit);

    Article article(int id);

    Page<Article> articles(ArticleQo qo, boolean adm);

    void save(Article article) throws ServiceException;

    void remove(int id);

}
