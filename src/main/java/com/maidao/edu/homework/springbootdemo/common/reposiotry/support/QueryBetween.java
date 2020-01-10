package com.maidao.edu.homework.springbootdemo.common.reposiotry.support;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:QueryBetween
 * 类描述:TODO
 **/
public class QueryBetween<T extends Comparable<?>> {

    public T before;
    public T after;

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }

    public T getAfter() {
        return after;
    }

    public void setAfter(T after) {
        this.after = after;
    }

}
