package com.maidao.edu.homework.baseexercise.chapter03.order.sercive;

import com.maidao.edu.homework.baseexercise.chapter03.order.moder.Account;
import com.maidao.edu.homework.baseexercise.chapter03.order.moder.Order;
import com.maidao.edu.homework.baseexercise.chapter03.order.moder.Product;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 20:16
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:OrderServer
 * 类描述:订单接口的一个具体实现类
 **/
public class OrderServer implements OrderServerInterface {

    public static void creatOrder() {

        Order o = new Order();
        Product p = new Product();
        Account a = new Account();


        o.getProductname();
        o.getNumber();

        p.getProductname();
        p.getProducprice();
        p.getRepertory();

        a.getRemainmoney();

    }
}
