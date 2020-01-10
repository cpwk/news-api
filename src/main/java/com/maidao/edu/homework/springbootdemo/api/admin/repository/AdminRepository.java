package com.maidao.edu.homework.springbootdemo.api.admin.repository;

import com.maidao.edu.homework.springbootdemo.api.admin.model.Admin;
import com.maidao.edu.homework.springbootdemo.common.reposiotry.BaseRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:08
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:AdminRepository
 * 类描述:实现与数据库的交互，持久层是介于业务逻辑层和数据库之间的，访问数据库并为业务处理提供或保存数据
 **/
public interface AdminRepository extends BaseRepository<Admin, Integer> {//第一个参数是实体类，第二个参数是此实体类主键类型

    Admin findByUsername(String username);
}
