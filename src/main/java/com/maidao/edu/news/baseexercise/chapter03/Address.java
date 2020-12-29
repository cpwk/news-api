package com.maidao.edu.news.baseexercise.chapter03;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-06 15:48
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:Address
 * 类描述:代表地址的类
 **/
public class Address {

    private String country = null;
    private String province = null;
    private String city = null;
    private String street = null;
    private String postal = null;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    @Override
    public String toString() {
        return "国家为：" + getCountry() + "---" + "省份为：" + getProvince() + "---" + "城市为：" + getCity() + "---" + "邮政编码为：" + getPostal();
    }

}
