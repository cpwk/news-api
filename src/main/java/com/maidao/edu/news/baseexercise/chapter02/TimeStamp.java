package com.maidao.edu.news.baseexercise.chapter02;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 11:36
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:TimeStamp
 * 类描述:获取各种时间戳
 **/

public class TimeStamp {

    public static void main(String[] args) {

        TimeStamp t = new TimeStamp();

        t.todayTimeStamp();
        t.todayStartTimeStamp();
        t.todayEndTimeStamp();
        t.yesterdayTimeStamp();
        t.yesterdayStartTimeStamp();
        t.yesterdayEndTimeStamp();

    }

    public void todayTimeStamp() {

        //获取当前时间的毫秒数
        //Date d = new Date();
        //System.out.println("当前时间毫秒数：" + d.getTime());
        System.out.println("当前时间毫秒数为：" + LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }

    public void yesterdayTimeStamp() {

        //获取当前时间前一天的毫秒数
        //Calendar c = Calendar.getInstance();
        //c.add(Calendar.DATE, -1);
        //Date da = c.getTime();
        //System.out.println("当前时间前一天的毫秒数：" + da.getTime());
        System.out.println("当前时间前一天的毫秒数：" + LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }

    public void todayStartTimeStamp() {

        System.out.println("今天开始时间的毫秒数：" + LocalDate.now().atTime(0, 0, 0).toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }

    public void todayEndTimeStamp() {

        System.out.println("今天结束时间的毫秒数：" + LocalDate.now().atTime(23, 59, 59, 999999999).toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }

    public void yesterdayStartTimeStamp() {

        System.out.println("昨天开始时间的毫秒数：" + LocalDate.now().atTime(0, 0, 0).minusDays(1).toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }

    public void yesterdayEndTimeStamp() {

        System.out.println("昨天结束时间的毫秒数：" + LocalDate.now().atTime(23, 59, 59, 999999999).minusDays(1).toInstant(ZoneOffset.of("+8")).toEpochMilli());

    }
}
