package com.maidao.edu.news.api.article.service;


import com.maidao.edu.news.api.article.model.Article;
import com.maidao.edu.news.api.article.qo.ArticleQo;
import com.maidao.edu.news.api.article.repository.IArticleRepository;
import com.maidao.edu.news.common.entity.Constants;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ArticleService
 * 类描述:Article其中一个实现类
 **/
@Service
public class ArticleService implements IArticleService {

    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public List<Article> getTopArticles(int limit) {
        ArticleQo qo = new ArticleQo();
        qo.setPageSize(limit);
        return articleRepository.findAll(qo).getContent();
    }

    @Override
    public void addVisit(int id) {
        articleRepository.addVisit(id);
    }

    @Override
    public Article article(int id) {
        return articleRepository.getOne(id);
    }

    @Override
    public Page<Article> articles(ArticleQo qo, boolean adm) {

        if (adm) {
            qo.setStatus(0);
        }
        return articleRepository.findAll(qo);
    }

    @Override
    public void save(Article article) throws ServiceException {

        if (StringUtils.isEmpty(article.getTitle())) {
            throw new ServiceException(0);//"请填写标题"
        }
        if (StringUtils.isEmpty(article.getImg())) {
            throw new ServiceException(0);//"请上传封面图"
        }
        if (StringUtils.isEmpty(article.getIntro())) {
            throw new ServiceException(0);//"请填写简介"
        }
        if (StringUtils.isEmpty(article.getContent())) {
            throw new ServiceException(0);//"请填写文章内容"
        }

        if (article.getId() == null) {
            article.setCreatedAt(System.currentTimeMillis());
        }
        if (article.getStatus() == null) {
            article.setStatus(Constants.STATUS_OK);
        }
        if (article.getVisitNum() == null) {
            article.setVisitNum(0);
        }
        articleRepository.save(article);
    }

    @Override
    public void remove(int id) {
        articleRepository.deleteById(id);
    }

}
