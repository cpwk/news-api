package com.maidao.edu.homework.baseexercise.chapter03;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 17:08
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Student
 * 类描述:子类Student
 **/
public class Student extends Person {

    private String math = null;
    private String english = null;

    public Student(String name, String address, String sex, String age, String math, String english) {

        super(name, address, sex, age);
        this.math = math;
        this.english = english;
    }

    public static void main(String[] args) {

        Student st = new Student("纳兹", "阿斯兰特界、伊修迦尔大陆、菲欧雷王国、Fairy Tail公会", "男", "777", "0", "0");
        System.out.println(st.toString());
    }

    @Override
    public String toString() {

        return "姓名为：" + getName() + "---" + "地址为：" + getAddress() + "---" + "性别为：" + getSex()
                + "---" + "年龄为：" + getAge() + "---" + "数学成绩为：" + this.math + "---" + "英语成绩为：" + this.english;
    }
}
