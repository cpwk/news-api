package com.maidao.edu.homework.baseexercise.chapter04;

import com.maidao.edu.homework.baseexercise.chapter03.Dog;
import com.maidao.edu.homework.baseexercise.chapter03.Person;

import java.util.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 18:44
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:CollectionsExercise
 * 类描述:Collections测试练习
 **/
public class CollectionsExercise {

    public static void main(String[] args) {

        List l = Collections.emptyList();
        Set s = Collections.emptySet();
        Map m = Collections.emptyMap();

        Person p = new Person("贝吉塔");
        Set<Person> s1 = Collections.singleton(p);

        Dog d = new Dog("土狗");
        Map<String, Dog> m1 = Collections.singletonMap("第一个元素", d);

        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        Collections.sort(a);

        List<Dog> a1 = new ArrayList<>();
        a1.add(new Dog("好狗"));
        a1.add(new Dog("坏狗"));
        Collections.sort(a1);

        Collections.sort(a1, new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

    }
}
