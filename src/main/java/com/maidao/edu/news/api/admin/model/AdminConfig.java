package com.maidao.edu.news.api.admin.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-06 16:32
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:AdminConfig
 * 类描述:admin的相关配置
 **/

@Component
@ConfigurationProperties(prefix = "admin", ignoreInvalidFields = false)//指定配置文件为admin
public class AdminConfig {
    private String salt;

    private Integer sessionDays;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getSessionDays() {
        return sessionDays;
    }

    public void setSessionDays(Integer sessionDays) {
        this.sessionDays = sessionDays;
    }
}
