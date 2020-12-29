package com.maidao.edu.news.baseexercise.chapter03.order.moder;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 20:14
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:Order
 * 类描述:订单实体类
 **/
public class Order {

    private Product productname = null;
    private long number = 0;

    public Order() {
    }

    public Order(Product productname, long number) {
        this.productname = productname;
        this.number = number;
    }

    public Product getProductname() {
        return productname;
    }

    public void setProductname(Product productname) {
        this.productname = productname;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
