package com.maidao.edu.homework.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 17:00
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:PrintSquare
 * 类描述:打印10*10空心正方形
 **/
public class PrintSquare {

    public static void main(String[] args) {

        PrintSquare p = new PrintSquare();
        p.printSquare();
    }

    public void printSquare() {

        for (int i = 1; i <= 10; i++) {
            if (i > 1 && i < 10) {
                System.out.println("*                 *");
            } else {
                System.out.println("* * * * * * * * * *");
            }
        }
    }
}
