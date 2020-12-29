package com.maidao.edu.news.springbootdemo.api.admin.service;

import com.maidao.edu.news.springbootdemo.api.admin.model.Admin;
import com.maidao.edu.news.springbootdemo.api.admin.model.AdminSession;
import com.maidao.edu.news.springbootdemo.api.admin.model.Role;
import com.maidao.edu.news.springbootdemo.api.admin.qo.AdminSessionQo;
import com.maidao.edu.news.springbootdemo.common.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:08
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdminService
 * 类描述:服务层接口
 **/

public interface AdminService {

    Map signIn(Admin admin, String ip) throws Exception;

    AdminSession findByToken(String token) throws Exception;

    List<Admin> findAllAdmin();

    Page<AdminSession> adminSessions(AdminSessionQo qo) throws Exception;

    Admin findById(Integer id) throws Exception;

    AdminSession adminSession(String token);

    Admin admin(int id, boolean init);

    List<Role> roles(boolean init);

    void removeAdmin(int id) throws Exception;

    void updatePassword(String password, String oldPassword) throws ServiceException;

    void save_admin(Admin admin) throws ServiceException;

    void remove_role(int id);

    Role role(int id);

    void save_role(Role role);
}
