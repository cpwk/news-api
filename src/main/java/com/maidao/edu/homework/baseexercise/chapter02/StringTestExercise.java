package com.maidao.edu.homework.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 09:02
 * Version 1.8.0_211
 * 项目名称：java-se
 * 类名称:StringTestExercise
 * 类描述:String类方法测试
 **/
public class StringTestExercise {
    public static void main(String[] args) {
        String s = "abcdefg";
        String t = "hijklmn";
        String[] arrStr = new String[]{"a", "b", "c"};
        System.out.println(s.charAt(0));
        System.out.println(s.codePointAt(1));
        System.out.println(s.compareTo(t));
        System.out.println(s.equals(t));
        System.out.println(s.equalsIgnoreCase(s));
        System.out.println(s.startsWith(t));
        System.out.println(s.endsWith(t));
        System.out.println(s.indexOf(97));
        System.out.println(s.lastIndexOf(98));
        System.out.println(s.length());
        System.out.println(s.replace(s, t));
        System.out.println(s.substring(1, 4));
        System.out.println(s.toUpperCase());
        System.out.println(s.trim());
        System.out.println(String.join("-", arrStr));

        StringBuilder sb = new StringBuilder();
        sb.append("I").append(" love").append(" you!");
        System.out.println(sb);
        System.out.println(sb.append(s));
        System.out.println(sb.reverse());
        System.out.println(sb.replace(0, 3, s));
        System.out.println(sb.toString());
    }
}
