package com.maidao.edu.homework.baseexercise.chapter02;

import java.time.LocalDate;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 11:31
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:OutputData
 * 类描述:输出当前时间的日期
 **/
public class OutputData {

    public static void main(String[] args) {

        OutputData o = new OutputData();
        o.outputData();

    }

    public void outputData() {
        LocalDate data = LocalDate.now();
        System.out.println("当前日期为：" + data);
    }
}
