package com.maidao.edu.homework.baseexercise.chapter01;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 15:15
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:LeapYear
 * 类描述:判断一个年份是否为闰年
 **/
public class LeapYear {

    public static void main(String[] args) {

        LeapYear l = new LeapYear();

        System.out.println("请输入需要判断的年份：");
        Scanner in = new Scanner(System.in);
        int year = in.nextInt();

        l.leapYear(year);

    }

    public void leapYear(int year) {

        if (year % 4 == 0 && year % 100 != 0) {
            System.out.println(year + "年是闰年");
        } else {
            System.out.println(year + "年不是闰年");
        }
    }
}
