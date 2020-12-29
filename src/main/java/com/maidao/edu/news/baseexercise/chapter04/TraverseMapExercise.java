package com.maidao.edu.news.baseexercise.chapter04;

import com.maidao.edu.news.baseexercise.chapter03.Dog;

import java.util.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 17:30
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:TraverseMapExercise
 * 类描述:遍历Map集合
 **/
public class TraverseMapExercise {

    public static void main(String[] args) {

        Map<String, Dog> m = new HashMap<>();

        m.put("第一个元素", new Dog("二哈", "黑色", "3"));
        m.put("第二个元素", new Dog("牧羊", "灰色", "3"));

        Iterator i = m.keySet().iterator();
        while (i.hasNext()) {
            Object o = i.next();
            Dog d = m.get(o);
            System.out.println(d.toString());
        }

        Collection<Dog> c = m.values();
        Iterator<Dog> i1 = c.iterator();
        while (i1.hasNext()) {
            Dog d = i1.next();
            System.out.println(d.toString());
        }

        Set<Map.Entry<String, Dog>> s = m.entrySet();
        Iterator<Map.Entry<String, Dog>> i3 = s.iterator();

        while (i3.hasNext()) {

            Map.Entry<String, Dog> m1 = i3.next();

            String s2 = m1.getKey();
            System.out.println(s2);

            Dog d = m1.getValue();
            System.out.println(d.toString());
        }

        for (Map.Entry<String, Dog> item : m.entrySet()) {
            String s1 = item.getKey();
            System.out.println(s1);

            Dog d = item.getValue();
            System.out.println(d.toString());
        }
    }
}
