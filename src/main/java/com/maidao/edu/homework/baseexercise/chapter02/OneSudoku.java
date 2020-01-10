package com.maidao.edu.homework.baseexercise.chapter02;

/**
 * 创建人:chenpeng
 * 创建时间:2019-07-04 17:17
 * Version 1.8.0_211
 * 项目名称：java se
 * 类名称:OneSudoku
 * 类描述:一维数独游戏
 **/
public class OneSudoku {

    public static void search(int[] num) {

        OneSudoku dimensional = new OneSudoku();

        for (int x = 0; x < num.length; x++) {
            if (num[x] == 0) {
                for (int j = 1; j <= 9; j++) {
                    if (dimensional.judge(j, num) == false) {
                        num[x] = j;
                    }
                }
            }
        }
        for (int y : num) {
            System.out.println(y);
        }
    }

    public static void main(String[] args) {

        int[] num = {0, 3, 0, 0, 7, 8, 0, 0, 4};
        search(num);
    }

    public boolean judge(int j, int[] num) {
        boolean re = false;
        for (int x : num) {
            if (j == x) {
                re = true;
            }
        }
        return re;
    }
}

