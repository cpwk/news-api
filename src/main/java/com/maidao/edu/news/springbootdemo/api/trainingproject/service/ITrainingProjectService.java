package com.maidao.edu.news.springbootdemo.api.trainingproject.service;

import com.maidao.edu.news.springbootdemo.api.trainingproject.model.TrainingProject;
import com.maidao.edu.news.springbootdemo.api.trainingproject.qo.TrainingProjectQo;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITrainingProjectService {

    List<TrainingProject> getTopTrainingProjects(int limit);

    TrainingProject trainingProject(int id);

    Page<TrainingProject> trainingProjects(TrainingProjectQo qo, boolean adm);

    void save(TrainingProject trainingProject) throws ServiceException;

    void remove(int id);

}
