package com.maidao.edu.homework.springbootdemo.api.trainee.repository;


import com.maidao.edu.homework.springbootdemo.api.trainee.model.TraineeSession;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITraineeSessionRepository extends BaseRepository<TraineeSession, Integer> {

    TraineeSession findByToken(String token);

}