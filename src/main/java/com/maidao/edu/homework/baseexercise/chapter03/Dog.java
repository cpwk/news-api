package com.maidao.edu.homework.baseexercise.chapter03;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 16:00
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Dog
 * 类描述:设计一个Dog类
 **/
public class Dog implements Comparable {

    private String name = null;
    private String colour = null;
    private String age = null;

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, String colour, String age) {
        this.name = name;
        this.colour = colour;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名为：" + getName() + "---" + "颜色为：" + getColour() + "---" + "年龄为：" + getAge();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
