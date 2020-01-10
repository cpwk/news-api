package com.maidao.edu.homework.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 17:34
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:SelectSort
 * 类描述:选择排序
 **/
public class SelectSort {

    public static void main(String[] args) {

        BubblingSort b = new BubblingSort();
        int[] arr = b.input();

        SelectSort s = new SelectSort();
        System.out.println("排序之后的结果为：");
        b.print(s.selectSort(arr));

    }

    public int[] selectSort(int[] arr) {

        int len = arr.length;
        int minindex;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            minindex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minindex]) {
                    minindex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minindex];
            arr[minindex] = temp;
        }
        return arr;
    }
}
