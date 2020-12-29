package com.maidao.edu.news.baseexercise.chapter03;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 18:13
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:VehicleDemo
 * 类描述:创建Car与Bus类实例并分别调用start()和stop()方法
 **/
public class VehicleDemo {

    public static void main(String[] args) {

        Car car = new Car();
        car.start();
        car.stop();

        Bus bus = new Bus();
        bus.start();
        Bus.stop();
    }
}
