package com.maidao.edu.homework.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 17:11
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:PrintMultiplicationTable
 * 类描述:打印9*9乘法表
 **/
public class PrintMultiplicationTable {

    public static void main(String[] args) {

        PrintMultiplicationTable p = new PrintMultiplicationTable();
        p.print();

    }

    public void print() {

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
    }

}
