package com.maidao.edu.homework.baseexercise.chapter02;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 15:03
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:CheckPrimeNumber
 * 类描述:判断素数
 **/
public class CheckPrimeNumber {

    public static void main(String[] args) {

        CheckPrimeNumber c = new CheckPrimeNumber();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要判断的数字：");
        int x = sc.nextInt();

        c.checkprimeNumber(x);

    }

    public void checkprimeNumber(int x) {

        boolean flage = false;

        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                flage = false;
                break;
            } else {
                flage = true;
            }
        }

        if (flage == true) {
            System.out.println(x + "是一个素数");
        } else {
            System.out.println(x + "不是一个素数");
        }

    }
}
