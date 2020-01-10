package com.maidao.edu.homework.baseexercise.chapter04;

import java.util.HashSet;
import java.util.Set;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 14:58
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:SaveRandomNumber
 * 类描述:保存20个40以内的随机整数
 **/
public class SaveRandomNumber {
    public static void main(String[] args) {

        SaveRandomNumber s = new SaveRandomNumber();
        s.saveRandomNumber();

    }

    public void saveRandomNumber() {
        Set<Integer> s = new HashSet<>();
        while (s.size() < 20) {
            s.add((int) (0 + Math.random() * (40 - 0 + 1)));
        }
        for (Integer i : s
        ) {
            System.out.println(i.toString());
        }
    }
}
