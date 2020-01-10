package com.maidao.edu.homework.baseexercise.chapter02;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 13:50
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:CountAge
 * 类描述:计算年龄
 **/
public class CountAge {

    public static void main(String[] args) {

        CountAge c = new CountAge();

        c.countAge();

    }

    public void countAge() {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入出生年份：");
        int yearofbirth = sc.nextInt();

        System.out.println("请输入出生月份：");
        int monthofbirth = sc.nextInt();

        System.out.println("请输入出生日份：");
        int dayofbirth = sc.nextInt();

        LocalDate birthday = LocalDate.of(yearofbirth, monthofbirth, dayofbirth);

        Period p1 = Period.between(birthday, LocalDate.now());
        System.out.println("距离您出生已经过去：" + p1.getYears() + "年" + p1.getMonths() + "个月零" + p1.getDays() + "天");

    }
}
