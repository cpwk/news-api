package com.maidao.edu.news.baseexercise.chapter01;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 16:10
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:ReplaceChar
 * 类描述:字符串的替换
 **/
public class ReplaceChar {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("请输入原始字符串,字符之间请用空格隔开：");
        String str = in.nextLine();

        System.out.println("请输入需要替换的字符串：");
        String str1 = in.nextLine();

        System.out.println("请输入需要替换的目标字符串：");
        String str2 = in.nextLine();

        ReplaceChar r = new ReplaceChar();
        r.replaceChar(str, str1, str2);
    }

    public void replaceChar(String str, String str1, String str2) {

        System.out.println("原始字符串为：");
        System.out.println(str);

        System.out.println("替换之后的字符串为：");
        System.out.println(str.replace(str1, str2));
    }

}
