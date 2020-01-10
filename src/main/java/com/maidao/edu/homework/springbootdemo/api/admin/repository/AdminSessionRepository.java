package com.maidao.edu.homework.springbootdemo.api.admin.repository;

import com.maidao.edu.homework.springbootdemo.api.admin.model.AdminSession;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 10:34
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdminSessionRepository
 * 类描述:实现与数据库的交互，持久层是介于业务逻辑层和数据库之间的，访问数据库并为业务处理提供或保存数据
 **/
public interface AdminSessionRepository extends BaseRepository<AdminSession, Integer> {

    AdminSession findByToken(String token);

}
