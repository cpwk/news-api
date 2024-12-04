package com.maidao.edu.news.common.authority;

import com.maidao.edu.news.api.admin.model.Admin;
import com.maidao.edu.news.api.admin.model.AdminSession;
import com.maidao.edu.news.api.admin.model.AdminSessionWrap;
import com.maidao.edu.news.api.admin.service.AdminService;
import com.maidao.edu.news.api.user.model.UserSession;
import com.maidao.edu.news.api.user.service.UserService;
import com.maidao.edu.news.common.exception.ErrorCode;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.util.StringUtils;
import com.maidao.edu.news.common.context.Contexts;
import com.maidao.edu.news.common.context.SessionWrap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:SessionUtil
 * 类描述:会话公用的工具类
 **/

public class SessionUtil {

    public static Map<String, SessionWrap> map = new HashMap<>();
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    public static SessionWrap getSession(String token) {
        return map.get(token);
    }

    public static boolean tokenTimeout(String token) {
        if (map.get(token) == null) {
            return true;
        } else {
            SessionWrap wrap = map.get(token);
            if (wrap == null) {
                return true;
            }
            if (wrap instanceof AdminSessionWrap) {
                AdminSession session = ((AdminSessionWrap) wrap).getAdminSession();
                return session == null || session.getExpireAt() <= (new Date().getTime());
            } else {
                return true;
            }
        }

    }

    public static void putSession(String token, SessionWrap sess) {
        if (map == null || map.isEmpty()) {
            map = new HashMap<>();
        }
        map.put(token, sess);
    }

    public static void removeSession(String token) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (token.equals(key)) {
                iterator.remove();
                map.remove(key);
            }
        }
    }

    public boolean checkUserToken(String token) throws Exception {
        UserSession session = userService.findByToken(token);
        if (session == null) {
            return false;
        }
        if (session.getExpireAt() < System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public boolean checkAdminToken(String token) throws Exception {
        AdminSession session = adminService.findByToken(token);
        if (session == null) {
            return false;
        }
        if (session.getExpireAt() < System.currentTimeMillis()) {
            return false;
        }
        Admin admin = adminService.admin(session.getAdminId(), false);
        AdminSessionWrap wrap = new AdminSessionWrap(admin, session);
        Contexts.get().setSession(wrap);

        return true;
    }

    public SessionWrap adminPermissionCheck(Enum type, String token, String permission) throws ServiceException {

        if (tokenTimeout(token)) {
            if (type == AdminType.ADMIN) {
                AdminSession session = adminService.adminSession(token);

                if (session != null && session.getExpireAt() > (new Date().getTime())) {
                    Admin admin = adminService.admin(session.getAdminId(), true);
                    if (admin != null && admin.getStatus() == 1) {
                        AdminSessionWrap wrap = new AdminSessionWrap(admin, session);
                        putSession(token, wrap);
                        return wrap;
                    } else {
                        throw new ServiceException(ErrorCode.NO_PERMISSION);
                    }
                } else {
                    throw new ServiceException(ErrorCode.SESSIONTIMEOUT);
                }
            }
        }

        {
            boolean pass = false;

            SessionWrap wrap = getSession(token);
            if (wrap == null) {
                throw new ServiceException(ErrorCode.SESSIONTIMEOUT);
            }

            if (StringUtils.isEmpty(permission) || permission.equals("NONE")) {
                pass = true;
            } else {
                String permissions = "999";
                if (wrap instanceof AdminSessionWrap) {
                    Admin admin = ((AdminSessionWrap) wrap).getAdmin();
                    permissions = admin.getRole().getPermissions();
                }

                if (!(StringUtils.isEmpty(permissions) || permissions.length() <= 1)) {
                    String[] ps = permissions.split(",");
                    for (String p : ps) {
                        if (p.equals(permission)) {
                            pass = true;
                        }
                    }
                }
            }

            if (pass) {
                return wrap;
            } else {
                throw new ServiceException(ErrorCode.NO_PERMISSION);
            }
        }
    }
}