package com.maidao.edu.homework.baseexercise.chapter04;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 15:26
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:LinkedListExercise
 * 类描述:LinkedList集合方法测试练习
 **/
public class LinkedListExercise {

    public static void main(String[] args) {

        List l = new LinkedList();

        l.add("露西");
        l.add("中国");
        l.add("女");
        l.add("18");

        System.out.println(l.get(3));

        for (int i = 0; i < l.size(); i++)
            System.out.println(l.get(i));

        for (Object o : l)
            System.out.println(o);

        Iterator<String> o1 = l.iterator();
        while (o1.hasNext()) {
            String s = o1.next();
            System.out.println(s);
        }

    }
}
