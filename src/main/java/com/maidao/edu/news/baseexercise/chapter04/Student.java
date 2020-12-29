package com.maidao.edu.news.baseexercise.chapter04;


/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 16:07
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:Student
 * 类描述:排序用到的学生类
 **/
public class Student {


    int id;
    int stgrade;
    String stname;

    public Student(int id, int stgrade, String stname) {
        this.id = id;
        this.stgrade = stgrade;
        this.stname = stname;
    }

    @Override
    public String toString() {
        return "学号:" + this.id + "---" + "姓名:" + this.stname + "---" + "成绩:" + this.stgrade;
    }

}

