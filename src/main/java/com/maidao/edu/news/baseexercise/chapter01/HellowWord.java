package com.maidao.edu.news.baseexercise.chapter01;

public class HellowWord {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("缺少参数");
        } else {
            if ("cn".equals(args[0])) {
                System.out.println("你好");
            } else if ("en".equals(args[0])) {
                System.out.println("This is my first procedure.");
                System.out.println("HellowWord!!!");
            } else {
                System.out.println("参数错误");
            }
        }
    }
}
