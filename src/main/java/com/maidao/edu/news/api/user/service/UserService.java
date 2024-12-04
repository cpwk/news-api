package com.maidao.edu.news.api.user.service;

import com.maidao.edu.news.api.user.model.User;
import com.maidao.edu.news.api.user.model.UserSession;
import com.maidao.edu.news.api.user.model.UserSessionWrap;
import com.maidao.edu.news.api.user.qo.UserQo;
import com.maidao.edu.news.api.user.qo.UserSessionQo;
import com.maidao.edu.news.common.code.model.VCode;
import org.springframework.data.domain.Page;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:08
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:UserService
 * 类描述:用户服务层接口
 **/

public interface UserService {

    void signUp(User user, String key, String smsCode) throws Exception;

    UserSessionWrap signIn(String unknown, String password, VCode vCode, String ip) throws Exception;

    UserSession findByToken(String token) throws Exception;

    String forgetPassword(String mobile, VCode vCode) throws Exception;

    void resetPassword(String mobile, String password, String key, String smsCode) throws Exception;

    void updatePassword(String mobile, String password, String newpassword, VCode vCode) throws Exception;

    void deleteById(Integer id) throws Exception;

    User findById(Integer id) throws Exception;

    Page<User> findAllUser(UserQo userQo) throws Exception;

    Page<UserSession> userSessions(UserSessionQo qo) throws Exception;
}
