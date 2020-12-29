package com.maidao.edu.news.baseexercise.chapter02;

import java.util.Objects;

public class People {

    private String id;
    private String name;
    private int age;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    public People(String id, String name, int age) {
        this.setId(id);
        this.setName(name);
        this.setAge(age);
    }

    public static void main(String[] args) {
        People p1 = new People();
        People p2 = new People();
        System.out.println(p1.equals(p2));

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "id为：" + id + '\'' + "姓名为：" + name + '\'' + "年龄为：" + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(id, people.id) &&
                Objects.equals(name, people.name) &&
                Objects.equals(age, people.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
