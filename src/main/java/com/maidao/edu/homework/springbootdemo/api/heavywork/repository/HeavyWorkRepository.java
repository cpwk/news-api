package com.maidao.edu.homework.springbootdemo.api.heavywork.repository;


import com.maidao.edu.homework.springbootdemo.api.heavywork.model.HeavyWork;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:HeavyWorkRepository
 * 类描述:HeavyWork持久层接口
 **/
public interface HeavyWorkRepository extends JpaRepository<HeavyWork, Integer> {
}
