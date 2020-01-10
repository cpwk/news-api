package com.maidao.edu.homework.springbootdemo.api.course.repository;


import com.maidao.edu.homework.springbootdemo.api.course.model.Chapter;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:IChapterRepository
 * 类描述:chapter持久层接口
 **/
public interface IChapterRepository extends BaseRepository<Chapter, Integer> {

    @Transactional
    void deleteByCourseId(int courseId);

}