package com.maidao.edu.homework.springbootdemo.api.article.repository;


import com.maidao.edu.homework.springbootdemo.api.article.model.Article;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:IArticleRepository
 * 类描述:Article持久层接口
 **/
public interface IArticleRepository extends BaseRepository<Article, Integer> {

    @Transactional
    @Modifying
    @Query("update Article set visitNum= visitNum+1 where id =:id")
    public void addVisit(@Param(value = "id") Integer id);

}