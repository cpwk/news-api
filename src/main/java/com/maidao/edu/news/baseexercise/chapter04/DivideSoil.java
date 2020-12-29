package com.maidao.edu.news.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 15:18
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:DivideSoil
 * 类描述:有一块⻓168米宽64米的土地，将这块地分隔成大小相等的正方形，
 * 编程计算满足以上条件的情况下，能分隔的最大正方形边⻓是多少。(用递归函数实现)
 **/
public class DivideSoil {

    public static void main(String[] args) {
        int length = 168;
        int width = 64;


        DivideSoil d = new DivideSoil();
        int i = d.divideSoil(length, width);
        System.out.println("最大边长为：" + i);
    }

    public int divideSoil(int length, int width) {
        if (length % width == 0) {
            return width;
        } else {
            return divideSoil(width, length % width);
        }
    }
}




