package com.maidao.edu.news.springbootdemo.api.trainer.repository;


import com.maidao.edu.news.springbootdemo.api.trainer.model.Trainer;
import com.maidao.edu.news.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public interface ITrainerRepository extends BaseRepository<Trainer, Integer> {

    Trainer findByUsernameAndStatus(String username, byte status);

    Trainer findByEmailAndStatus(String email, byte status);

    Trainer findByMobileAndStatus(String mobile, byte status);

}
