package com.maidao.edu.news.common.util;

import com.sunnysuperman.commons.locale.LocaleUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:WebUtils
 * 类描述:TODO
 **/
public class WebUtils {

    public static String getHeader(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (value != null) {
            return value;
        }
        value = request.getHeader(key);
        return value;
    }

    private static boolean isValidIp(String ip) {
        return ip != null && ip.length() > 0 && !ip.equalsIgnoreCase("unknown");
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null) {
            List<String> proxies = StringUtils.split(ip, ",");
            String remoteIp = proxies.size() > 0 ? proxies.get(0).trim() : null;
            if (isValidIp(remoteIp)) {
                return remoteIp;
            }
        }
        ip = request.getRemoteAddr();
        if (isValidIp(ip)) {
            return ip;
        }
        return null;
    }

    public static String getLocale(HttpServletRequest request, String key) {
        String locale = getHeader(request, key);
        locale = LocaleUtil.formatLocale(locale);
        if (StringUtils.isNotEmpty(locale)) {
            return locale;
        }
        Locale requestLocale = request.getLocale();
        if (requestLocale == null) {
            return null;
        }
        String lang = requestLocale.getLanguage();
        if (StringUtils.isEmpty(lang)) {
            return null;
        }
        if (StringUtils.isEmpty(requestLocale.getCountry())) {
            return lang;
        }
        return lang + '_' + requestLocale.getCountry();
    }

}
