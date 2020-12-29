package com.maidao.edu.news.springbootdemo.api.trainingproject.service;

import com.maidao.edu.news.springbootdemo.api.trainingproject.model.TrainingProject;
import com.maidao.edu.news.springbootdemo.api.trainingproject.qo.TrainingProjectQo;
import com.maidao.edu.news.springbootdemo.api.trainingproject.repository.ITrainingProjectRepository;
import com.maidao.edu.news.springbootdemo.common.entity.Constants;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
@Service
public class TrainingProjectService implements ITrainingProjectService {

    @Autowired
    private ITrainingProjectRepository custcaseRepository;

    @Override
    public TrainingProject trainingProject(int id) {
        return custcaseRepository.getOne(id);
    }

    @Override
    public List<TrainingProject> getTopTrainingProjects(int limit) {
        TrainingProjectQo qo = new TrainingProjectQo();
        qo.setPageSize(limit);
        qo.setOntop(1);
        return custcaseRepository.findAll(qo).getContent();
    }

    @Override
    public Page<TrainingProject> trainingProjects(TrainingProjectQo qo, boolean adm) {
        if (adm) {
            qo.setStatus(0);
        }
        return custcaseRepository.findAll(qo);

    }

    @Override
    public void save(TrainingProject custcase) throws ServiceException {

        if (custcase.getId() == null) {
            custcase.setCreatedAt(System.currentTimeMillis());
        }
        if (custcase.getStatus() == null) {
            custcase.setStatus(Constants.STATUS_OK);
        }
        custcaseRepository.save(custcase);
    }

    @Override
    public void remove(int id) {
        custcaseRepository.deleteById(id);
    }
}
