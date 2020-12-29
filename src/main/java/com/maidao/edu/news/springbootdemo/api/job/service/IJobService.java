package com.maidao.edu.news.springbootdemo.api.job.service;


import com.maidao.edu.news.springbootdemo.api.job.model.Job;
import com.maidao.edu.news.springbootdemo.api.job.qo.JobQo;

import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IJobService
 * 类描述:业务逻辑层接口
 **/
public interface IJobService {

    Job job(int id);

    List<Job> jobs(JobQo qo);

    void save(Job job);

    void remove(int id);

}
