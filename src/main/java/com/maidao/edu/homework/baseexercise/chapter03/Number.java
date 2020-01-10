package com.maidao.edu.homework.baseexercise.chapter03;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 16:40
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Number
 * 类描述:模拟简易计算器
 **/
public class Number {

    private Double x = null;
    private Double y = null;

    public Number(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入第一个需要运算的整数：");
        Double x = sc.nextDouble();

        System.out.println("请输入第二个需要运算的整数：");
        Double y = sc.nextDouble();

        Number nb = new Number(x, y);

        System.out.println("运算结果如下所示：");
        System.out.println(x + "+" + y + "=" + nb.plus(x, y));
        System.out.println(x + "-" + y + "=" + nb.minus(x, y));
        System.out.println(x + "*" + y + "=" + nb.multiply(x, y));
        System.out.println(x + "/" + y + "=" + nb.division(x, y));
    }

    public Double plus(Double x, Double y) {
        return x + y;
    }

    public Double minus(Double x, Double y) {
        return x - y;
    }

    public Double multiply(Double x, Double y) {
        return x * y;
    }

    public Double division(Double x, Double y) {
        return x / y;
    }
}
