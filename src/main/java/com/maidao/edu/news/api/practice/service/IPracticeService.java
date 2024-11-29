package com.maidao.edu.news.api.practice.service;


import com.maidao.edu.news.api.practice.model.Practice;
import com.maidao.edu.news.api.practice.qo.PracticeQo;
import com.maidao.edu.news.api.practice.qo.PracticeWo;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IPracticeService
 * 类描述:业务逻辑层接口
 **/
public interface IPracticeService {

    //practice
    Practice practice(int id);

    List<Practice> practices(PracticeQo qo, PracticeWo wo);

    void save_practice(Practice practice) throws Exception;

    void save_practices(List<Practice> practices) throws Exception;

    void remove_practice(int id) throws Exception;

    int countByLessonId(int lessonId);
}
