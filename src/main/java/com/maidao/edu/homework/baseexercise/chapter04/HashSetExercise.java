package com.maidao.edu.homework.baseexercise.chapter04;

import com.maidao.edu.homework.baseexercise.chapter03.Dog;
import com.maidao.edu.homework.baseexercise.chapter03.Person;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 18:25
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:HashSetExercise
 * 类描述:HashSet集合测试练习
 **/
public class HashSetExercise {

    public static void main(String[] args) {

        HashSet h = new HashSet();

        String s = new String("1");
        Dog d = new Dog("贵宾", "棕色", "3");
        Person p = new Person("卡卡罗特");

        h.add(s);
        h.add(d);
        h.add(p);

        System.out.println(h.contains(s));

        System.out.println(h.isEmpty());

        Iterator iterator = h.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        System.out.println(h.remove(d));

        System.out.println(h.size());

        h.clear();
    }

}
