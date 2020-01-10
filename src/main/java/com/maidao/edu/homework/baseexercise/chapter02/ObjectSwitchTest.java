package com.maidao.edu.homework.baseexercise.chapter02;

public class ObjectSwitchTest {

    public static void swap(People p1, People p2) {

        System.out.println("swap方法中置换前");
        print(p1);
        print(p2);

        People p3 = p1;
        p1 = p2;
        p2 = p3;

        System.out.println("swap方法中置换后");
        print(p1);
        print(p2);
    }

    public static void print(People p) {
        System.out.println("id为：" + p.getId() + "姓名为：" + p.getName() + "年龄为：" + p.getAge());

    }

    public static void main(String[] args) {
        People p1 = new People("p1", "p1", 1);
        People p2 = new People("p2", "p2", 2);
        System.out.println("man方法中置换前");
        print(p1);
        print(p2);

        swap(p1, p2);

        System.out.println("man方法中置换前");
        print(p1);
        print(p2);
    }
}

