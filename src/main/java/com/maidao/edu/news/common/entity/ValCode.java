package com.maidao.edu.news.common.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ValCode
 * 类描述:TODO
 **/
public class ValCode {

    private Long key;
    private String code;
    private Integer userType;   // 1.trainer 2.trainee
    private Integer accountType;  // 1.email 2.sms
    private String account;

    public ValCode() {

    }

    public ValCode(String code) {
        this.code = code;
    }

    public ValCode(String code, Integer userType, Integer accountType, String account) {
        this.code = code;
        this.userType = userType;
        this.accountType = accountType;
        this.account = account;
    }

    public ValCode(long key, String code, Integer userType, Integer accountType, String account) {
        this.code = code;
        this.userType = userType;
        this.accountType = accountType;
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }
}
