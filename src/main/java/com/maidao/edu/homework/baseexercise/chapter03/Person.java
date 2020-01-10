package com.maidao.edu.homework.baseexercise.chapter03;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 17:01
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:Person
 * 类描述:父类Person
 **/
public class Person {

    private String name = null;
    private String address = null;
    private String sex = null;
    private String age = null;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String address, String sex, String age) {
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "姓名为：" + this.name + "---" + "地址为：" + this.address + "---" + "性别为：" + this.sex + "---" + "年龄为：" + this.age;
    }
}
