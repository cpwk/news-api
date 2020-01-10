package com.maidao.edu.homework.baseexercise.chapter04;

import com.maidao.edu.homework.baseexercise.chapter03.Dog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 16:38
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:HashMapExercise
 * 类描述:HashMap集合测试练习
 **/
public class HashMapExercise {

    public static void main(String[] args) {

        Map<String, Dog> m1 = new HashMap<>();
        HashMap<String, String> m2 = new HashMap<>();

        m1.put("m1第一个元素", new Dog("萨摩", "大红", "3"));
        m2.put("m2第一个元素", "地下城与勇士");

        System.out.println(m1.containsKey("m1第一个元素"));
        System.out.println(m2.get("m2第一个元素"));


        System.out.println(m1.size());

        System.out.println(m1.isEmpty());


        Set<String> keySet = m2.keySet();
        for (String s : keySet) {
            System.out.println(s);
        }

        Collection<Dog> doglist = m1.values();
        for (Dog d : doglist) {
            System.out.println(d.toString());
        }

        m2.remove("m1第一个元素");

        m1.clear();

        System.out.println("陈鹏".hashCode());
    }

}
