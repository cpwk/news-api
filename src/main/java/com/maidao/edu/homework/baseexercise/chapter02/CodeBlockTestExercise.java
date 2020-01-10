package com.maidao.edu.homework.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 10:58
 * Version 1.8.0_211
 * 项目名称：java se
 * 类名称:CodeBlockTestExercise
 * 类描述:代码块作用域测试练习
 **/
public class CodeBlockTestExercise {

    public static void main(String[] args) {

        int a = 1;

        {
            int b = 2;
            System.out.println(b);
            System.out.println(a);
        }
        //System.out.println(b);
    }
}
