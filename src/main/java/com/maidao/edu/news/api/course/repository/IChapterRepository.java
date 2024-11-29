package com.maidao.edu.news.api.course.repository;


import com.maidao.edu.news.common.reposiotry.BaseRepository;
import com.maidao.edu.news.api.course.model.Chapter;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IChapterRepository
 * 类描述:chapter持久层接口
 **/
public interface IChapterRepository extends BaseRepository<Chapter, Integer> {

    @Transactional
    void deleteByCourseId(int courseId);

}