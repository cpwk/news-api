package com.maidao.edu.homework.springbootdemo.api.banner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:BannerType
 * 类描述:banner类型枚举类
 **/

public enum BannerType {

    HOME(1), PRODUCT(2), SERVICE(3), CASE(4), COOPERATION(5), ARTICLE(6), BLOG(7), ABOUT(8);

    private int val;

    private BannerType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
