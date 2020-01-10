package com.maidao.edu.homework.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 15:37
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:BankUser
 * 类描述:银行储户信息
 **/
public class BankUser {
    private String Id;
    private String username;
    private float balance;

    public BankUser(String id) {
        Id = id;
    }

    public BankUser(String id, float balance) {
        Id = id;
        this.balance = balance;
    }

    public BankUser(String id, String username, float balance) {
        Id = id;
        this.username = username;
        this.balance = balance;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
