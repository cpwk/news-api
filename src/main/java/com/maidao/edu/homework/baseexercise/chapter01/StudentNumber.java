package com.maidao.edu.homework.baseexercise.chapter01;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 14:25
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:StudentNumber
 * 类描述:分解学号并分别输出
 **/
public class StudentNumber {

    public static void main(String[] args) {

        System.out.println("请输入'8位数的'学号：");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();

        StudentNumber s = new StudentNumber();
        s.studentNumber(id);

    }

    public void studentNumber(int id) {
        int year = id / 10000;
        int periods = id / 100 % 100;
        int serial = id % 100;

        System.out.println("年份：" + year);
        System.out.println("期数：" + periods);
        System.out.println("序号：" + serial);
    }
}
