package com.maidao.edu.homework.baseexercise.chapter04;

import java.util.Scanner;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-08 19:43
 * Version 1.8.0_211
 * 项目名称：com.maidao.edu.homework
 * 类名称:BubblingSort
 * 类描述:冒泡排序
 **/
public class BubblingSort {

    public static void main(String[] args) {

        BubblingSort bs = new BubblingSort();

        int[] arr = bs.input();
        bs.bubblingSort(arr);
        System.out.println("排序之后的结果为：");
        bs.print(arr);
    }

    public void bubblingSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {          // 定义外层循环

            for (int j = 0; j < arr.length - i - 1; j++) {  // 定义内层循环

                if (arr[j] > arr[j + 1]) {                  // 比较相邻元素
                    // 下面的三行代码用于交换两个元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void print(int[] arr2) {

        for (int i = 0; i < arr2.length; i++) {

            System.out.print(arr2[i] + " ");             // 打印元素和空格
        }
    }

    public int[] input() {

        System.out.println("请输入需要排序的数，用空格隔开:");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        String[] str = s.split(" ");
        int[] arr1 = new int[str.length];

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = Integer.valueOf(str[i]).intValue();
        }

        return arr1;
    }
}
