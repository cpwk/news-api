package com.maidao.edu.homework.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 19:16
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:InsertSort
 * 类描述:插入排序
 **/
public class InsertSort {

    public static void main(String[] args) {

        InsertSort i = new InsertSort();
        BubblingSort b = new BubblingSort();
        int[] arr = b.input();

        System.out.println("排序之后的结果为：");
        b.print(i.insertSort(arr));
    }

    public int[] insertSort(int[] arr) {
        int len = arr.length;
        int preindex;
        int current;

        for (int i = 1; i < len; i++) {
            preindex = i - 1;
            current = arr[i];
            while (preindex >= 0 && arr[preindex] > current) {
                arr[preindex + 1] = arr[preindex];
                preindex--;

            }
            arr[preindex + 1] = current;
        }

        return arr;
    }
}
