package com.maidao.edu.news.api.user.service;

import com.maidao.edu.news.api.user.model.User;
import com.maidao.edu.news.api.user.model.UserConfig;
import com.maidao.edu.news.api.user.model.UserSession;
import com.maidao.edu.news.api.user.model.UserSessionWrap;
import com.maidao.edu.news.api.user.qo.UserQo;
import com.maidao.edu.news.api.user.qo.UserSessionQo;
import com.maidao.edu.news.api.user.repository.UserRepository;
import com.maidao.edu.news.api.user.repository.UserSessionRepository;
import com.maidao.edu.news.common.code.model.CodeCache;
import com.maidao.edu.news.common.code.model.VCode;
import com.maidao.edu.news.common.code.service.VCodeService;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.util.DateUtils;
import com.maidao.edu.news.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.maidao.edu.news.api.user.model.UserErrors.*;
import static com.maidao.edu.news.common.code.model.CodeErrors.ERR_VCODE_INVALID;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:UserServicelmpl
 * 类描述:用户服务层接口的一个具体实现类
 **/

@Service
public class UserServicelmpl implements UserService {

    @Autowired
    private UserSessionRepository userSessionRepository;
    @Autowired
    private UserConfig userConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CodeCache codeCache;
    @Autowired
    private VCodeService vCodeService;

    @Override
    public UserSessionWrap signIn(String unknown, String password, VCode vCode, String ip) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.validatePassword(password)) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        if (!StringUtils.isChinaMobile(unknown) && !StringUtils.isEmail(unknown) && !StringUtils.validateNick(unknown)) {
            throw new ServiceException(ERR_USER_NOTEXIST);
        }
        User exist = null;
        if (StringUtils.isChinaMobile(unknown)) {
            exist = userRepository.findByMobile(unknown);
        } else if (StringUtils.isEmail(unknown)) {
            exist = userRepository.findByEmail(unknown);
        } else if (StringUtils.validateNick(unknown)) {
            exist = userRepository.findByNick(unknown);
        }
        if (exist == null) {
            throw new ServiceException(ERR_USER_NOTEXIST);
        }
        if (((StringUtils.encryptPassword(password, userConfig.getSalt())).equals(exist.getPassword())) != true) {
            throw new ServiceException(ERR_USER_PASSWORD_ERROR);
        }
        UserSession userSession = new UserSession();
        userSession.setUserId(exist.getId());
        userSession.setToken(StringUtils.getRandStr(64));
        Long now = System.currentTimeMillis();
        userSession.setSignInAt(now);
        userSession.setExpireAt(DateUtils.addDays(now, userConfig.getSessionDays()));
        userSession.setIp(ip);
        userSessionRepository.save(userSession);
        return new UserSessionWrap(exist, userSession);
    }

    @Override
    public void signUp(User user, String key, String smsCode) throws Exception {
        if (!(codeCache.getMobile(key).equals(user.getMobile()))) {
            throw new ServiceException(ERR_USER_MOBILE_DIFFER);
        }
        if (!(smsCode.equals(codeCache.getCode(key)))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.isChinaMobile(user.getMobile())) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);
        }
        if (!StringUtils.isEmail(user.getEmail())) {
            throw new ServiceException(ERR_USER_EMAIE_INVALID);
        }
        if (!StringUtils.validateNick(user.getNick())) {
            throw new ServiceException(ERR_USER_NICK_FORMAT);
        }
        if (!StringUtils.validatePassword(user.getPassword())) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        if (userRepository.findByMobile(user.getMobile()) != null) {
            throw new ServiceException(ERR_USER_MOBILE_USED);
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ServiceException(ERR_USER_EMAIE_USED);
        }

        user.setPassword(StringUtils.encryptPassword(user.getPassword(), userConfig.getSalt()));
        user.setCreatedAt(System.currentTimeMillis());
        userRepository.save(user);
    }

    @Override
    public UserSession findByToken(String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return userSessionRepository.findByToken(token);
    }

    @Override
    public String forgetPassword(String mobile, VCode vCode) throws Exception {
        if (!StringUtils.isChinaMobile(mobile)) {
            throw new ServiceException(ERR_USER_MOBILE_INVALID);
        }
        if (userRepository.findByMobile(mobile) == null) {
            throw new ServiceException(ERR_USER_NOTEXIST);
        }
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        return mobile;
    }

    @Override
    public void resetPassword(String mobile, String password, String key, String smsCode) throws Exception {
        if (!(smsCode.equals(codeCache.getCode(key)))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.validatePassword(password)) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        User exits = userRepository.findByMobile(mobile);
        exits.setPassword(StringUtils.encryptPassword(password, userConfig.getSalt()));
        userRepository.save(exits);
    }

    @Override
    public void updatePassword(String mobile, String password, String newpassword, VCode vCode) throws Exception {
        if (!(vCode.getCode().equals(vCodeService.getVCode(vCode.getKey()).getCode()))) {
            throw new ServiceException(ERR_VCODE_INVALID);
        }
        if (!StringUtils.validatePassword(password) || !StringUtils.validatePassword(newpassword)) {
            throw new ServiceException(ERR_USER_PASSWORD_FORMAT);
        }
        User exist = userRepository.findByMobile(mobile);
        if (((StringUtils.encryptPassword(password, userConfig.getSalt())).equals(exist.getPassword())) != true) {
            throw new ServiceException(ERR_USER_PASSWORD_ERROR);
        }
        exist.setPassword(StringUtils.encryptPassword(newpassword, userConfig.getSalt()));
        userRepository.save(exist);
    }

    @Override
    public Page<User> findAllUser(UserQo userQo) throws Exception {
        return userRepository.findAll(userQo);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<UserSession> userSessions(UserSessionQo qo) throws Exception {
        return userSessionRepository.findAll(qo);
    }
}