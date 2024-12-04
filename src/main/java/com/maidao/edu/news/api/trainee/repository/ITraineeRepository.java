package com.maidao.edu.news.api.trainee.repository;


import com.maidao.edu.news.common.reposiotry.BaseRepository;
import com.maidao.edu.news.api.trainee.model.Trainee;

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
