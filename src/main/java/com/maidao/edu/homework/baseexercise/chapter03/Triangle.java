package com.maidao.edu.homework.baseexercise.chapter03;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 19:23
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Triangle
 * 类描述:继承抽象类Shape，检查输入的三条边能否构成三角形，并可以计算三角形周长的Triangle类
 **/
public class Triangle extends Shape {

    private float l1;
    private float l2;
    private float l3;

    public static void main(String[] args) {

        Triangle t = new Triangle();
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入三角形的第一个边长：");
        float l1 = sc.nextFloat();
        t.setL1(l1);

        System.out.println("请输入三角形的第二个边长：");
        float l2 = sc.nextFloat();
        t.setL2(l2);

        System.out.println("请输入三角形的第三个边长：");
        float l3 = sc.nextFloat();
        t.setL3(l3);

        if (t.check() == true) {
            t.perimeter();
        } else {
            System.out.println("输入三条边构不成三角形，请重新输入！");
        }
    }

    public void setL1(float l1) {
        this.l1 = l1;
    }

    public void setL2(float l2) {
        this.l2 = l2;
    }

    public void setL3(float l3) {
        this.l3 = l3;
    }

    public void arer() {
    }

    public void perimeter() {

        float f = l1 + l2 + l3;
        System.out.println("三角形的周长为：" + f);
    }

    public boolean check() {

        if (l1 + l2 > l3 && l2 + l3 > l1 && l3 + l1 > l2)
            return true;
        else
            return false;
    }
}
