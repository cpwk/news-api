package com.maidao.edu.news.baseexercise.chapter03;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 19:56
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:Circle
 * 类描述:继承抽象类Shape，输入圆形的半径，可以计算圆形面积和周长的Circle类
 **/
public class Circle extends Shape {

    private float r;

    public static void main(String[] args) {

        Circle c = new Circle();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入圆形的半径：");
        float l = sc.nextFloat();
        c.setR(l);

        c.arer();
        c.perimeter();
    }

    public void setR(float r) {
        this.r = r;
    }

    public void arer() {
        System.out.println("圆形的面积为：" + Math.PI * r * r);
    }

    public void perimeter() {
        System.out.println("矩形的周长为：" + 2 * Math.PI * r);
    }
}
