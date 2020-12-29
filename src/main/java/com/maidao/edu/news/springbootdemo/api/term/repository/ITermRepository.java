package com.maidao.edu.news.springbootdemo.api.term.repository;

import com.maidao.edu.news.springbootdemo.api.term.model.Term;
import com.maidao.edu.news.springbootdemo.common.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITermRepository extends BaseRepository<Term, Integer> {

    Term findBySequence(String sequence);

    @Transactional
    @Modifying
    @Query("update Term set set_default= 0")
    void removeDefault();

    @Transactional
    @Modifying
    @Query("update Term set set_default= 1 where id =:id")
    void setDefault(@Param(value = "id") Integer id);

}