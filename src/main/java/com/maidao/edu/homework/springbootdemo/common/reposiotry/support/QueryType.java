package com.maidao.edu.homework.springbootdemo.common.reposiotry.support;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:QueryType
 * 类描述:TODO
 **/
public enum QueryType {

    EQUAL(false),
    BEWTEEN(false),
    LESS_THAN(false),
    LESS_THAN_EQUAL(false),
    GREATEROR_THAN(false),
    GREATEROR_THAN_EQUAL(false),
    NOT_EQUAL(false),
    IS_NULL(true),
    IS_NOT_NULL(true),
    RIGHT_LIKE(false),
    LEFT_LIKE(false),
    FULL_LIKE(false),
    DEFAULT_LIKE(false),
    NOT_LIKE(false),
    IN(false);

    //	是否可以为空
    private boolean isCanBeNull;

    private QueryType(boolean isCanBeNull) {
        this.isCanBeNull = isCanBeNull;
    }

    public boolean isCanBeNull() {
        return this.isCanBeNull;
    }
}
