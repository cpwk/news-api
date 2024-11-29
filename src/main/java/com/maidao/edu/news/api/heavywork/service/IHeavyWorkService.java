package com.maidao.edu.news.api.heavywork.service;


import com.maidao.edu.news.api.heavywork.model.HeavyWork;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:IHeavyWorkService
 * 类描述:业务逻辑层接口
 **/
public interface IHeavyWorkService {

    HeavyWork create(String owner) throws Exception;

    HeavyWork getById(Integer id, String secret) throws Exception;

    void updateProgress(Integer id, int progress);

    void updateFailed(Integer id, String errors);

    void updateSuccess(Integer id, String output);

}
