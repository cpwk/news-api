package com.maidao.edu.news.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 20:39
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:QuickSort
 * 类描述:快速排序
 **/
public class QuickSort {

    public static void main(String[] args) {

        QuickSort q = new QuickSort();
        BubblingSort b = new BubblingSort();
        int[] arr = b.input();

        System.out.println("排序之后的结果为：");
        b.print(q.quickSort(arr, 0, arr.length - 1));
    }

    public int[] quickSort(int[] a, int left, int right) {
        if (left > right)
            return a;
        int i = left;
        int j = right;
        int key = a[left];
        while (i < j) {
            while (i < j && a[j] >= key) {
                j--;
            }
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            while (i < j && a[i] < key) {
                i++;
            }
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = key;
        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
        return a;
    }
}
