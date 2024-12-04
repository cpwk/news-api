package com.maidao.edu.news.common.authority;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:Permission
 * 类描述:TODO
 **/

public class Permission {

    private String code;
    private String name;
    private String level;

    public Permission() {
        super();
    }

    public Permission(String code, String name, String level) {
        super();
        this.code = code;
        this.name = name;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}