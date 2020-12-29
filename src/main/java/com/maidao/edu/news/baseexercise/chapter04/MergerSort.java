package com.maidao.edu.news.baseexercise.chapter04;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-09 19:44
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.news
 * 类名称:MergerSort
 * 类描述:归并排序
 **/
public class MergerSort {

    public static void main(String[] args) {

        MergerSort m = new MergerSort();
        BubblingSort b = new BubblingSort();
        int[] arr = b.input();

        System.out.println("排序之后的结果为：");
        b.print(m.mergerSort(arr, 0, arr.length - 1));
    }

    public int[] mergerSort(int[] a, int low, int high) {

        int mid = (low + high) / 2;

        if (low < high) {
            mergerSort(a, low, mid);
            mergerSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }

        return a;
    }

    public void merge(int[] a, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= high) {

            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];

            }

            while (i <= mid) {
                temp[k++] = a[i++];

            }

            while (j <= high) {
                temp[k++] = a[j++];
            }

            for (int x = 0; x < temp.length; x++) {
                a[x + low] = temp[x];
            }
        }
    }
}
