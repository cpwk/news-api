package com.maidao.edu.news.springbootdemo.api.trainee.repository;


import com.maidao.edu.news.springbootdemo.api.trainee.model.TraineeSession;
import com.maidao.edu.news.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITraineeSessionRepository extends BaseRepository<TraineeSession, Integer> {

    TraineeSession findByToken(String token);

}
