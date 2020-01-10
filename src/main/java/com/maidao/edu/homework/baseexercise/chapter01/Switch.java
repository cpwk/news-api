package com.maidao.edu.homework.baseexercise.chapter01;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 15:46
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Switch
 * 类描述:字符串存储顺序转换
 **/
public class Switch {

    public static void main(String[] args) {

        System.out.println("请输入需要反转的字符串：");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        Switch s = new Switch();
        s.switchString(str);

    }

    public void switchString(String str) {

        System.out.println("您输入的原始字符串为:" + str);

        StringBuffer arr = new StringBuffer(str);
        System.out.println("反转之后的字符串为:" + arr.reverse().toString());

    }
}
