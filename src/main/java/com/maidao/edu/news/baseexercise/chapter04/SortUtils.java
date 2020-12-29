package com.maidao.edu.news.baseexercise.chapter04;

import java.util.ArrayList;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-10 16:25
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:SortUtils
 * 类描述:编写一个工具类SortUtils.java, 其中对外开放冒泡排序、选择排序、插入排序、归并排序
 * 和快速排序五中排序算法对List集合进行排序。
 **/

public class SortUtils {

    public static void main(String[] args) {

        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add(new Integer(16));
        l.add(new Integer(15));
        l.add(new Integer(14));
        l.add(new Integer(13));
        SortUtils s = new SortUtils();
        System.out.println(s.selectSort(l).toString());

        ArrayList<Integer> l1 = new ArrayList<Integer>();
        l1.add(new Integer(12));
        l1.add(new Integer(11));
        l1.add(new Integer(10));
        l1.add(new Integer(9));
        System.out.println(s.bubblingSort(l1).toString());

        ArrayList<Integer> l2 = new ArrayList<Integer>();
        l2.add(new Integer(8));
        l2.add(new Integer(7));
        l2.add(new Integer(6));
        l2.add(new Integer(5));
        System.out.println(s.insertSort(l2).toString());

        ArrayList<Integer> l3 = new ArrayList<Integer>();
        l3.add(new Integer(4));
        l3.add(new Integer(3));
        l3.add(new Integer(2));
        l3.add(new Integer(1));

        System.out.println(s.quickSort(l3, 0, l3.size() - 1).toString());

    }

    public ArrayList<Integer> bubblingSort(ArrayList<Integer> list) {
        int i = 0;
        int j = 0;
        int temp = 0;
        for (i = 0; i < list.size(); i++) {
            for (j = 0; j < list.size() - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> selectSort(ArrayList<Integer> list) {
        Integer len = list.size();
        Integer minIndex;
        Integer temp;
        for (int i = 0; i < len - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
        return list;
    }

    public ArrayList<Integer> insertSort(ArrayList<Integer> list) {
        Integer len = list.size();
        Integer preIndex;
        Integer currnt;
        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            currnt = list.get(i);
            while (preIndex >= 0 && list.get(preIndex).compareTo(currnt) > 0) {
                list.set(preIndex + 1, list.get(preIndex));
                preIndex--;
            }
            list.set(preIndex + 1, currnt);
        }
        return list;
    }

    public ArrayList<Integer> quickSort(ArrayList<Integer> list, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int key = list.get(i);
            while (i < j) {
                while (i < j && list.get(j).compareTo(key) >= 0) {
                    j--;
                }
                if (i < j) {
                    list.set(i++, list.get(j));
                }
                while (i < j && list.get(i).compareTo(key) < 0) {
                    i++;
                }
                if (i < j) {
                    list.set(j--, list.get(i));
                }
            }
            list.set(i, key);
            quickSort(list, left, i - 1);
            quickSort(list, i + 1, right);
        }
        return list;
    }
}