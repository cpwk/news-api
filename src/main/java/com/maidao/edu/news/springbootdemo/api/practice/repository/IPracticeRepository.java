package com.maidao.edu.news.springbootdemo.api.practice.repository;

import com.maidao.edu.news.springbootdemo.api.practice.model.Practice;
import com.maidao.edu.news.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IPracticeRepository
 * 类描述:持久层接口
 **/
public interface IPracticeRepository extends BaseRepository<Practice, Integer> {

    Integer countAllByLessonIdEquals(int lessonId);

}