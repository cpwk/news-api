package com.maidao.edu.news.baseexercise.chapter04;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-11 09:18
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:SortByGrade
 * 类描述:T某班有40个学生，学号为190101~190140,全部参加Java集合阶段测试，给出所有同学
 * 的成绩(可随机产生，范围为50~100),请编写从程序将本班各位同学的成绩从高到低
 * 排序打印输出。
 * 注:成绩相同时学号较小的优先打印，打印的信息包括学号、姓名(统一 为“学生i"[i=1,2, 3, 4,，.. 40])和成绩。
 **/

public class SortByGrade implements Comparator<Student> {
    public static void main(String[] args) {
        LinkedList<Student> l = new LinkedList<>();
        for (int i = 190101, j = 01; i <= 190140; j++, i++) {
            l.add(new Student(i, (int) (50 + Math.random() * (100 - 50 + 1)), "同学" + String.format("%02d", j)));
        }
        Collections.sort(l, new SortByGrade());
        for (Student s : l) {
            System.out.println(s);
        }
    }

    @Override
    public int compare(Student s1, Student s2) {
        if (s1.stgrade == s2.stgrade) {
            return s1.stname.compareTo(s2.stname);
        }
        if (s1.stgrade > s2.stgrade) {
            return -1;
        }
        if (s1.stgrade < s2.stgrade) {
            return 1;
        }
        return 0;
    }
}
