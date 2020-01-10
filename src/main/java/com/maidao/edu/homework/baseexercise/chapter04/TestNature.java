package com.maidao.edu.homework.baseexercise.chapter04;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 10:01
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:TestNature
 * 类描述:测试ArrayList和LinkedList在添加，访问，删除元素时的性能
 **/
public class TestNature {

    public static void main(String[] args) {

        TestNature t = new TestNature();

        t.testArrayListAdd();
        t.testLinkedListAdd();

        t.testArrayListDelete();
        t.testLinkedListDelete();

        t.testArrayListFind();
        t.testLinkedListFind();
    }

    public void testArrayListAdd() {
        List l = new ArrayList();
        long starttime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (int i = 0; i < 1000000; i++) {
            l.add(i);
        }
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("ArrayList添加一百万个元素用时：" + (endtime - starttime) + "毫秒");
    }

    public void testLinkedListAdd() {
        List l = new LinkedList();
        long starttime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        for (int i = 0; i < 1000000; i++) {
            l.add(i);
        }
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("LinkedList添加一百万个元素用时：" + (endtime - starttime) + "毫秒");

    }

    public void testArrayListDelete() {
        List l = new ArrayList();
        for (int i = 0; i < 10000000; i++) {
            l.add(i);
        }
        long starttime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        l.remove(5000000);
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("ArrayList在一千万个元素里删除指定元素用时：" + (endtime - starttime) + "毫秒");

    }

    public void testLinkedListDelete() {
        List l = new LinkedList();
        for (int i = 0; i < 10000000; i++) {
            l.add(i);
        }
        long starttime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        l.remove(5000000);
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("LinkedList在一千万个元素里删除指定元素用时：" + (endtime - starttime) + "毫秒");

    }

    public void testArrayListFind() {
        List l = new ArrayList();
        for (int i = 0; i < 10000000; i++) {
            l.add(i);
        }
        long starttime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        l.lastIndexOf(0);
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("ArrayList在一千万个元素里逆序寻找元素用时：" + (endtime - starttime) + "毫秒");

    }

    public void testLinkedListFind() {
        List l = new LinkedList();
        for (int i = 0; i < 10000000; i++) {
            l.add(i);
        }
        long starttime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        l.lastIndexOf(0);
        long endtime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("LinkedList在一千万个元素里逆序寻找元素用时：" + (endtime - starttime) + "毫秒");

    }
}
