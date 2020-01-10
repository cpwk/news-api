package com.maidao.edu.homework.baseexercise.chapter01;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 15:22
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Flower
 * 类描述:输出100-999的水仙花数
 **/
public class Flower {

    public static void main(String[] args) {

        Flower f = new Flower();

        for (int i = 100; i <= 999; i++) {

            if (f.flower(i) == true) {
                System.out.println(i);
            }
        }

    }

    private boolean flower(int x) {

        int a = x / 100;
        int b = x / 10 % 10;
        int c = x % 10;

        if (x == a * a * a + b * b * b + c * c * c) {
            return true;
        } else {
            return false;
        }

    }
}
