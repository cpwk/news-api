package com.maidao.edu.homework.baseexercise.chapter04;

import com.maidao.edu.homework.baseexercise.chapter03.Person;
import com.maidao.edu.homework.baseexercise.chapter03.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 14:31
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:ArrayListExercise
 * 类描述:ArrayList集合方法测试练习
 **/
public class ArrayListExercise {

    public static void main(String[] args) {

        List l = new ArrayList();
        List l1 = new ArrayList(20);
        List l2 = new ArrayList(l);

        Person p = new Person("格雷", "阿斯兰特界、伊修迦尔大陆、菲欧雷王国、Fairy Tail公会", "男", "17");
        Student s = new Student("纳兹", "阿斯兰特界、伊修迦尔大陆、菲欧雷王国、Fairy Tail公会", "男", "777", "0", "0");

        l.add(p);
        l.add(s);

        List<Student> l3 = new ArrayList<>();

        l3.add(s);
        l3.add(new Student("艾露莎", "阿斯兰特界、伊修迦尔大陆、菲欧雷王国、Fairy Tail公会", "女", "999", "100", "100"));

        System.out.println(l3.size());

        Student s2 = l3.get(1);

        System.out.println(l3.indexOf(s2));

        System.out.println(l3.contains(s));

        List<Student> l4 = new ArrayList<>();
        l4.addAll(l3);

        Object[] s3 = l4.toArray();

        Person[] p1 = new Person[l4.size()];
        l4.toArray(p1);

        Person[] p2 = l4.toArray(new Person[l4.size()]);

        System.out.println(l4.isEmpty());

        String s4 = new String("夏露露");
        l.add(new String("温蒂"));
        l.set(1, s4);
        System.out.println(l.indexOf("夏露露"));
        System.out.println(l.lastIndexOf("温蒂"));

        l3.remove(0);

        l3.clear();

    }
}
