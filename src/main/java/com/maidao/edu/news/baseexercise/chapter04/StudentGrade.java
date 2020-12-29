package com.maidao.edu.news.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 16:10
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:StudentGrade
 * 类描述:学生成绩信息类
 **/
public class StudentGrade {
    private int studentnumber;
    private String studentname;
    private float studentgrade;

    public StudentGrade(int studentnumber, String studentname, float studentgrade) {
        this.studentnumber = studentnumber;
        this.studentname = studentname;
        this.studentgrade = studentgrade;
    }

    public int getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(int studentnumber) {
        this.studentnumber = studentnumber;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public float getStudentgrade() {
        return studentgrade;
    }

    public void setStudentgrade(float studentgrade) {
        this.studentgrade = studentgrade;
    }
}
