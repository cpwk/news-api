package com.maidao.edu.homework.springbootdemo.api.news.service;

import com.maidao.edu.homework.springbootdemo.api.news.model.News;
import com.maidao.edu.homework.springbootdemo.api.news.qo.NewsQo;
import com.maidao.edu.homework.springbootdemo.api.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-30 09:54
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:NewsServicelmpl
 * 类描述:服务层具体实现类
 **/

@Service
public class NewsServicelmpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News findById(Integer id) throws Exception {
        return newsRepository.findById(id).get();
    }

    @Override
    public Page<News> findAll(NewsQo newsQo) throws Exception {
        return newsRepository.findAll(newsQo);
    }

    @Override
    public void save(News news) throws Exception {
        news.setCreatedAt(System.currentTimeMillis());
        newsRepository.save(news);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        newsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() throws Exception {
        newsRepository.deleteAll();
    }
}