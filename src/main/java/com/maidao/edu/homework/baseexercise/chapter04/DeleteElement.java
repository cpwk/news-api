package com.maidao.edu.homework.baseexercise.chapter04;

import com.maidao.edu.homework.baseexercise.chapter02.People;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 11:08
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:DeleteElement
 * 类描述:遍历集合并删除指定元素
 **/
public class DeleteElement {

    public static void main(String[] args) {
        DeleteElement d = new DeleteElement();
        d.deleteElement();
    }

    public void deleteElement() {

        List<People> l = new ArrayList<>();
        l.add(new People("路飞"));
        l.add(new People("索隆"));
        l.add(new People("娜美"));
        l.add(new People("香吉士"));
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName() == "路飞") {
                l.remove(i);
            }
        }

        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }

    }
}
