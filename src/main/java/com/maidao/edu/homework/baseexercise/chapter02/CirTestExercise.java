package com.maidao.edu.homework.baseexercise.chapter02;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 11:06
 * Version 1.8.0_211
 * 项目名称：java se
 * 类名称:CirTestExercise
 * 类描述:循环练习
 **/
public class CirTestExercise {
    public static void main(String[] args) {
        int a = 1;
        while (a <= 10) {
            System.out.println(a);
            a++;
        }

        do {
            System.out.println(a);
            a++;
        } while (a <= 6);

        String[] s = {"a", "b", "c"};
        for (String ch : s) {
            System.out.print(ch + ":");
        }
        System.out.println();
        System.out.println("请输入一个整数：");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        switch (x) {
            case 1:
                System.out.println("您输入的是：" + x);
                break;
            case 2:
                System.out.println("您输入的是：" + x);
                break;
            case 3:
                System.out.println("您输入的是：" + x);
                break;
            default:
                System.out.println("您输入的数大于3！");
        }
    }
}
