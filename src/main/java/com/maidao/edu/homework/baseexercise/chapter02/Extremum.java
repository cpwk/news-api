package com.maidao.edu.homework.baseexercise.chapter02;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 11:36
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Extremum
 * 类描述:输出三个数之中的最值
 **/
public class Extremum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入需要比较的第一个数：");
        int fistnumber = sc.nextInt();

        System.out.println("请输入需要比较的第二个数：");
        int secondnumber = sc.nextInt();

        System.out.println("请输入需要比较的第三个数：");
        int thirdnumber = sc.nextInt();

        Extremum e = new Extremum();

        e.max(fistnumber, secondnumber, thirdnumber);
        e.min(fistnumber, secondnumber, thirdnumber);

    }

    public void max(int a, int b, int c) {

        System.out.println("最大值为：" + Math.max(Math.max(a, b), c));
    }

    public void min(int a, int b, int c) {

        System.out.println("最小值为：" + Math.min(Math.min(a, b), c));

    }

}
