package com.maidao.edu.news.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-03 08:54
 * Version 1.8.0_211
 * 项目名称：java-se
 * 类名称:Man
 * 类描述:继承类测试
 **/
public class Man extends People {
    private String run;

    public static void main(String[] args) {
        Man m = new Man();
        m.setName("SuperMan");
        m.setRun("两条腿走路");
        System.out.println("通过调用父类的方法：" + m.getName());
        System.out.println("通过调用子类的方法：" + m.getRun());
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }
}