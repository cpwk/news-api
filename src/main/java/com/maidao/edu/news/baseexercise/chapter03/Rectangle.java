package com.maidao.edu.news.baseexercise.chapter03;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 18:35
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:Rectangle
 * 类描述:继承抽象类Shape并实现计算矩形面积和周长的Rectangle类
 **/
public class Rectangle extends Shape {

    private float length;
    private float wide;

    public static void main(String[] args) {

        Rectangle r = new Rectangle();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入矩形的长：");
        float l = sc.nextFloat();
        r.setLength(l);

        System.out.println("请输入矩形的宽：");
        float w = sc.nextFloat();
        r.setWide(w);

        r.arer();
        r.perimeter();
    }

    public void setWide(float wide) {
        this.wide = wide;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void arer() {
        System.out.println("矩形的面积为：" + wide * length);
    }

    public void perimeter() {
        float f = wide * 2 + length * 2;
        System.out.println("矩形的周长为：" + f);
    }
}
