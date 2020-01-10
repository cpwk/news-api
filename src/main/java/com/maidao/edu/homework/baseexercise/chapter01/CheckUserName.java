package com.maidao.edu.homework.baseexercise.chapter01;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 16:26
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:CheckUserName
 * 类描述:检查用户名是否合法
 **/
public class CheckUserName {

    public static void main(String[] args) {

        CheckUserName c = new CheckUserName();

        System.out.println("请输入用户名：");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        c.checkUserName(str);

    }

    public void checkUserName(String str) {
        String regex = "^[a-zA-Z][A-Za-z0-9_]{7,19}";
        if (str.matches(regex)) {
            System.out.println("用户名合法");
        } else {
            System.out.println("用户名不合法!");
        }
    }
}
