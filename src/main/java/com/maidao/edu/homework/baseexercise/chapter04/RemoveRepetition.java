package com.maidao.edu.homework.baseexercise.chapter04;

import java.util.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 14:49
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:RemoveRepetition
 * 类描述:去除List中的重复元素并且不改变顺序
 **/
public class RemoveRepetition {

    public static void main(String[] args) {
        RemoveRepetition r = new RemoveRepetition();
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(3);
        r.removeRepetition(l);
    }

    public void removeRepetition(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println("去重之后：" + list);
    }
}
