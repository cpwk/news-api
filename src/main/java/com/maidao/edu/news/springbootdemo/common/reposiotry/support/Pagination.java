package com.maidao.edu.news.springbootdemo.common.reposiotry.support;

import java.util.Collection;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:05
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:TODO
 * 类描述:TODO
 **/
public class Pagination<T> {
    private Collection<T> items;
    private int offset;
    private int limit;
    private int total;

    public Pagination(List<T> list, int total, DataQueryObjectPage page) {
        this.items = list;
        this.offset = page.getOffSet();
        this.limit = page.getPageSize();
        this.total = total;
    }

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.items = items;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
