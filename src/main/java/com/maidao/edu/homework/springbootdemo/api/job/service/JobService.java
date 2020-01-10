package com.maidao.edu.homework.springbootdemo.api.job.service;

import com.maidao.edu.homework.springbootdemo.api.job.model.Job;
import com.maidao.edu.homework.springbootdemo.api.job.qo.JobQo;
import com.maidao.edu.homework.springbootdemo.api.job.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:JobService
 * 类描述:业务逻辑实现类
 **/
@Service
public class JobService implements IJobService {

    @Autowired
    private IJobRepository jobRepository;

    @Override
    public Job job(int id) {
        return jobRepository.getOne(id);
    }

    @Override
    public List<Job> jobs(JobQo qo) {
        return jobRepository.findAll(qo);
    }

    @Override
    public void save(Job job) {
        if (job.getId() == null) {
            job.setCreatedAt(System.currentTimeMillis());
        }
        jobRepository.save(job);
    }

    @Override
    public void remove(int id) {
        jobRepository.deleteById(id);
    }

}
