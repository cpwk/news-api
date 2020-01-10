package com.maidao.edu.homework.baseexercise.chapter02;

import java.util.Scanner;

/**
 * @program: MyCode
 * * 创建人:chenpeng
 * * 创建时间:2019-07-04 17:17
 * * Version 1.8.0_211
 * * 项目名称：java se
 * * 类名称:OneSudoku
 * * 类描述:二维数独游戏
 **/
public class TwoSudoku {
    public static final int N = 3;

    public static void main(String[] args) {

        int x[][] = {
                {0, 0, 4, 6, 0, 2, 0, 0, 0},
                {6, 0, 0, 0, 3, 0, 0, 0, 4},
                {0, 2, 0, 4, 0, 0, 0, 9, 0},
                {9, 8, 0, 0, 0, 0, 3, 5, 0},
                {1, 0, 3, 0, 0, 0, 0, 4, 0},
                {0, 6, 0, 0, 0, 0, 8, 0, 7},
                {0, 3, 0, 0, 2, 0, 0, 7, 0},
                {0, 0, 0, 0, 6, 1, 0, 0, 5},
                {0, 0, 9, 3, 0, 0, 4, 0, 0}
        };
        long start = System.currentTimeMillis();
        int row = 0, col = 0;//第一个需要填充的位置
        while (x[row][col] != 0) {
            if (col == 8) {
                row++;
                col = 0;
            }
            col++;
        }
        boolean succes = fillCell(x, row, col);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println((succes ? "成功" : "失败") + "用时" + time + "毫秒");
        print(x);
    }

    private static int[][] init() {
        int[][] grid = new int[9][9];
        for (int i = 0; i < grid.length; i++) {
            System.out.print("请输入第" + (i + 1) + "行:");
            Scanner scan = new Scanner(System.in); // 从键盘接收数据
            String str = scan.next();
            while (str == null || str.length() != 9) {
                System.out.println("请重新输入第" + (i + 1) + "行：");
                scan = new Scanner(System.in); // 从键盘接收数据
                str = scan.next();
            }
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        return grid;
    }

    /**
     * 递归函数
     *
     * @param x 已填数组
     * @param r 目标横坐标
     * @param c 目标纵坐标
     * @return
     */
    private static boolean fillCell(int[][] x, int r, int c) {
        for (int i = 1; i <= 9; i++) {//第r行第c列从1开始到9逐个尝试
            x[r][c] = i;
            if (checkNum(x, r, c)) {//检查已有数字
                int rc[] = nextRC(x, r, c);
                if (rc[0] < 9 && rc[1] < 9) {
                    boolean next = fillCell(x, rc[0], rc[1]);//递归调用
                    if (next) {
                        return true;
                    } else {
                        continue;
                    }
                } else {
                    //最后一个单元格取数成功
                    return true;
                }
            }
        }
        x[r][c] = 0;    //1-9都不符合规则，恢复代填标识0
        return false;
    }

    /**
     * 下一个需要填充单元格坐标
     *
     * @param x
     * @param r
     * @param c
     * @return
     */
    private static int[] nextRC(int[][] x, int r, int c) {
        c++;
        if (c >= 9) {
            r++;
            c = 0;
        }
        if (r < 9 && x[r][c] != 0) {
            return nextRC(x, r, c);
        } else {
            int rc[] = {r, c};
            return rc;
        }
    }

    private static boolean checkNum(int[][] x, int r, int c) {
        for (int i = 0; i < 9; i++) {
            if (i != r && x[i][c] == x[r][c]) {
                return false;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (j != c && x[r][j] == x[r][c]) {
                return false;
            }
        }
        for (int i = r / 3 * 3; i < r / 3 * 3 + 3; i++) {
            for (int j = c / 3 * 3; j < c / 3 * 3 + 3; j++) {
                if (!(i == r && j == c) && x[i][j] == x[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void print(int[][] x) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
