package com.maidao.edu.news.baseexercise.chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 14:23
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:CheckIntersection
 * 类描述:检查交集并输出交集
 **/
public class CheckIntersection {

    public static void main(String[] args) {
        CheckIntersection c = new CheckIntersection();
        c.checkIntersection();
    }

    public void checkIntersection() {

        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        List<Integer> l1 = new ArrayList<>();
        l1.add(3);
        l1.add(4);
        l1.add(5);
        List<Integer> l2 = new ArrayList<>();

        for (Integer i : l) {
            for (Integer i1 : l1) {
                if (i.equals(i1)) {
                    l2.add(i);
                }
            }
        }

        for (Integer i : l2) {
            System.out.println(i);
        }
    }
}
