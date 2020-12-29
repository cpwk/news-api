package com.maidao.edu.news.springbootdemo.api.trainee.repository;


import com.maidao.edu.news.springbootdemo.api.trainee.model.Trainee;
import com.maidao.edu.news.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITraineeRepository extends BaseRepository<Trainee, Integer> {

    Trainee findByUsernameAndStatus(String username, byte status);

    Trainee findByEmailAndStatus(String email, byte status);

    Trainee findByMobileAndStatus(String mobile, byte status);

}
