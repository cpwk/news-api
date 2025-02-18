package com.maidao.edu.news.api.admin.service;

import com.maidao.edu.news.api.admin.authority.AdminContext;
import com.maidao.edu.news.api.admin.authority.AdminPermissionVO;
import com.maidao.edu.news.api.admin.model.*;
import com.maidao.edu.news.api.admin.qo.AdminSessionQo;
import com.maidao.edu.news.api.admin.repository.AdminRepository;
import com.maidao.edu.news.api.admin.repository.AdminSessionRepository;
import com.maidao.edu.news.api.admin.repository.IRoleRepository;
import com.maidao.edu.news.common.context.Context;
import com.maidao.edu.news.common.context.Contexts;
import com.maidao.edu.news.common.entity.Constants;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.ipseeker.IPSeekerUtil;
import com.maidao.edu.news.common.util.CollectionUtil;
import com.maidao.edu.news.common.util.DateUtils;
import com.maidao.edu.news.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.maidao.edu.news.api.admin.model.AdminErrors.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdminServicelmpl
 * 类描述:服务层具体实现类
 **/

@Service
public class AdminServicelmpl implements AdminService {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private AdminSessionRepository adminSessionRepository;
    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Map signIn(Admin adm, String ip) throws ServiceException {

        Admin admin = getAdmin(adm.getUsername());

        if (admin == null)
            throw new ServiceException(ERR_ADMIN_NOTEXIST);

        if (admin.getPassword().equals(StringUtils.getMD5(adm.getPassword(), adminConfig.getSalt()))) {
            if (admin.getStatus() == Constants.STATUS_OK) {
                String token = StringUtils.getToken(adminConfig.getSalt());
                AdminSession session = saveAdminSession(admin, token, ip);
                admin.setSigninAt(System.currentTimeMillis());
                adminRepository.save(admin);

                AdminSessionWrap wrap = new AdminSessionWrap(admin, session);

                Context context = Contexts.get();
                context.setSession(wrap);

                return CollectionUtil.arrayAsMap("admin", admin, "session", session);

            } else {
                throw new ServiceException(ERR_ADMIN_NOTEXIST);
            }
        } else {
            throw new ServiceException(ERR_ADMIN_PASSWORD_ERROR);
        }
    }

    @Override
    public AdminSession findByToken(String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return adminSessionRepository.findByToken(token);
    }

    @Override
    public Page<AdminSession> adminSessions(AdminSessionQo qo) throws Exception {
        return adminSessionRepository.findAll(qo);
    }

    @Override
    public Admin findById(Integer id) throws Exception {
        return adminRepository.findById(id).get();
    }

    @Override
    public AdminSession adminSession(String token) {
        return adminSessionRepository.findByToken(token);
    }

    @Override
    public Admin admin(int id, boolean init) {
        Admin admin = adminRepository.findById(id).get();
        if (init && admin != null)
            admin.setRole(roleRepository.getOne(admin.getRoleId()));
        return admin;
    }

    private Admin getAdmin(String username) {
        try {
            username = StringUtils.escapeSql(username);
            Admin admin = adminRepository.findByUsername(username);
            if (admin != null)
                admin.setRole(roleRepository.getOne(admin.getRoleId()));
            return admin;
        } catch (Exception e) {
            return null;
        }
    }

    private AdminSession saveAdminSession(Admin admin, String token, String ip) {
        AdminSession session = new AdminSession();
        session.setAdminId(admin.getId());
        session.setRole(admin.getRole().getName());
        session.setName(admin.getName());
        session.setToken(token);
        session.setSigninAt(new Date().getTime());
        session.setExpireAt(DateUtils.getDate_days(new Date(), Constants.SESSION_EXPIRE_DAYS).getTime());
        session.setIp(ip);
        session.setLocation(IPSeekerUtil.getFullLocation(ip));
        adminSessionRepository.save(session);
        return session;
    }

    @Override
    public List<Admin> findAllAdmin() {

        List<Admin> admins = adminRepository.findAll(new Sort(Sort.Direction.ASC, "roleId"));
        List<Role> roles = roles(false);

        for (Admin admin : admins) {
            for (Role role : roles) {
                if (admin.getRoleId().intValue() == role.getId().intValue())
                    admin.setRole(role);
            }
        }
        return admins;
    }

    @Override
    public List<Role> roles(boolean init) {
        List<Role> roles = roleRepository.findAll();
        if (init) {
            for (Role r : roles) {
                r.setPstr(AdminPermissionVO.initPermissionsByPs(r.getPermissions()));
            }
        }
        return roles;
    }

    @Override
    public void removeAdmin(int id) throws ServiceException {

        if (id == 1) {
            throw new ServiceException(ERR_ADMIN_UNREACHED_ERROR);
        }
        adminRepository.deleteById(id);
    }

    @Override
    public void updatePassword(String password, String oldPassword) throws ServiceException {

        AdminSessionWrap wrap = AdminContext.getSessionWrap();
        AdminSession session = wrap.getAdminSession();
        Admin admin = adminRepository.getOne(session.getAdminId());

        if (!admin.getPassword().equals(StringUtils.getMD5(oldPassword, adminConfig.getSalt()))) {
            throw new ServiceException(ERR_ADMIN_PASSWORD_ERROR);
        }
        admin.setPassword(StringUtils.getMD5(password, adminConfig.getSalt()));

        adminRepository.save(admin);
    }

    @Override
    public void save_admin(Admin admin) throws ServiceException {

        Admin oa = getAdmin(admin.getUsername());

        if (admin.getId() != null && admin.getId() > 0) {
            if (oa == null)
                throw new ServiceException(ERR_ADMIN_NOTEXIST);

            oa.setName(admin.getName());
            oa.setRoleId(admin.getRoleId());
            if (!StringUtils.isNull(admin.getPassword()))
                oa.setPassword(StringUtils.getMD5(admin.getPassword(), adminConfig.getSalt()));

            adminRepository.save(oa);
        } else {
            if (oa != null) {
                throw new ServiceException(ERR_ADMIN_USERNAME_USED);
            }
            admin.setPassword(StringUtils.getMD5(admin.getPassword(), adminConfig.getSalt()));
            admin.setStatus(Constants.ISTATUS_OK);
            adminRepository.save(admin);
        }
    }

    @Override
    public void remove_role(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role role(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void save_role(Role role) {
        roleRepository.save(role);
    }
}