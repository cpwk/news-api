package com.maidao.edu.homework.baseexercise.chapter02;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-05 11:19
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:DataTimeExercise
 * 类描述:jdk 1.8新特性日期时间测试练习
 **/
public class DataTimeExercise {

    public static void main(String[] args) {

        LocalDate ld = LocalDate.ofYearDay(2018, 100);
        System.out.println("2018年的第100天的日期是:" + ld);

        LocalDate data = LocalDate.now();
        LocalDate another = data.withYear(2018);
        System.out.println("2018年的今天的日期：" + another);

        System.out.println(data.isLeapYear() ? "今年是闰年" : "今年不是闰年");

        System.out.println(data.getYear() + "年" + data.getMonthValue() + "月有" + data.lengthOfMonth() + "天");

        System.out.println(data.getYear() + "年有" + data.lengthOfYear() + "天");

        LocalTime lt1 = LocalTime.now();
        System.out.println("当前时间：" + lt1);

        LocalTime lt2 = LocalTime.of(11, 11, 11);
        System.out.println("指定时间：" + lt2);

        LocalTime lt3 = LocalTime.ofSecondOfDay(10000);
        System.out.println("今天第10000毫秒的时间是：" + lt3);

        System.out.println("一个小时前：" + lt1.minusHours(1));
        System.out.println("当前时间：" + lt1);
        System.out.println("一个小时后：" + lt1.plusHours(1));

        LocalDateTime llt = LocalDateTime.now();
        System.out.println("当前日期时间:" + llt);
        System.out.println("去年今天的日期时间" + llt.minusYears(1));

        LocalDateTime llt1 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("当前时间:" + llt1);

        LocalDateTime llt2 = LocalDateTime.of(2018, 11, 11, 11, 11, 11);
        System.out.println("指定日期与时间：" + llt2);

        LocalDateTime llt3 = LocalDate.now().atTime(11, 11, 11);
        System.out.println("当天日期并指定时间：" + llt3);

        Instant i = Instant.now();
        System.out.println("当前时间：" + i);

        Instant i1 = Instant.ofEpochMilli(i.toEpochMilli());
        System.out.println("当前时间：" + i);
    }
}
