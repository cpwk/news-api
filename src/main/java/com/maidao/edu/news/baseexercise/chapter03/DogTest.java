package com.maidao.edu.news.baseexercise.chapter03;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 16:14
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:DogTest
 * 类描述:Dog测试类
 **/
public class DogTest extends Dog {

    public DogTest(String name, String colour, String age) {
        super(name, colour, age);
    }

    public static void main(String[] args) {
        Dog[] dogs = new Dog[3];
        dogs[0] = new Dog("萨摩", "雪白", "1");
        dogs[1] = new Dog("二哈", "黑白相间", "1");
        dogs[2] = new Dog("贵宾", "棕色", "1");
        for (Dog dog : dogs) {
            System.out.println(dog.toString());
        }
    }
}
