package com.maidao.edu.homework.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 09:16
 * Version 1.8.0_211
 * 项目名称：java-se
 * 类名称:NumberTestExercise
 * 类描述:基本数据类型测试
 **/
public class NumberTestExercise {

    private int number = 1_000_000_000;

    public static void main(String[] args) {

        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        System.out.println('\n');

        System.out.println("byte二进制位：" + Byte.SIZE);
        System.out.println("byte包装类：" + Byte.class);
        System.out.println("byte最小值：" + Byte.MIN_VALUE);
        System.out.println("byte最大值：" + Byte.MAX_VALUE);
        System.out.println('\n');

        System.out.println("short二进制位：" + Short.SIZE);
        System.out.println("short包装类：" + Short.class);
        System.out.println("short最小值：" + Short.MIN_VALUE);
        System.out.println("short最大值：" + Short.MAX_VALUE);
        System.out.println('\n');

        System.out.println("int二进制位：" + Integer.SIZE);
        System.out.println("int包装类：" + Integer.class);
        System.out.println("int最小值：" + Integer.MIN_VALUE);
        System.out.println("int最大值：" + Integer.MAX_VALUE);
        System.out.println('\n');

        System.out.println("long二进制位：" + Long.SIZE);
        System.out.println("long包装类：" + Long.class);
        System.out.println("long最小值：" + Long.MIN_VALUE);
        System.out.println("long最大值：" + Long.MAX_VALUE);
        System.out.println('\n');

        System.out.println("float二进制位：" + Float.SIZE);
        System.out.println("float包装类：" + Float.class);
        System.out.println("float最小值：" + Float.MIN_VALUE);
        System.out.println("float最大值：" + Float.MAX_VALUE);
        System.out.println('\n');

        System.out.println("double二进制位：" + Double.SIZE);
        System.out.println("double包装类：" + Double.class);
        System.out.println("double最小值：" + Double.MIN_VALUE);
        System.out.println("double最大值：" + Double.MAX_VALUE);
        System.out.println('\n');

        System.out.println("char二进制位：" + Character.SIZE);
        System.out.println("char包装类：" + Character.class);
        System.out.println("char最小值：" + (int) Character.MIN_VALUE);
        System.out.println("char最大值：" + (int) Character.MAX_VALUE);
        System.out.println("字符A对应的字符值：" + (int) 'A');
        System.out.println("数字97对应字符：" + "字符A对应的字符值：" + (char) 97);
        System.out.println('\n');
    }
}
