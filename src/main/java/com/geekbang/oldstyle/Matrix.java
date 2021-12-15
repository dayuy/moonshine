package com.geekbang.oldstyle;


import java.util.LinkedList;
import java.util.List;

/**
 * 原地旋转矩阵：leetcode: 48
 * 一个n*n的矩阵，顺时针旋转90度，必须原地旋转（直接修改，不能使用另一个矩阵变量，不能另外创造空间）
 *
 * 先看一道简单的例子：
 * 给出一个字符串s = "hello world labuladong", 得到一个**原地反转**所有单词的顺序"labuladong world hello"
 * 解1：常规方法是，把s先split，再reverse这些单词的顺序，最后join成句子。但是使用了额外空间，不是「原地反转」
 * 解2：正确的做法是，先将整个s反转，再分别将每个单词反转
 *
 *
 *
 * 所以，原地旋转矩阵，可以先进行镜像对称，再对矩阵每一行进行反转
 * **/
public class Matrix {
    public static void main(String[] args) {
        int[][] matrixArr = new int[][]{{5,1,9,11}, {2,4,8,10}, {13,3,6,7}, {15,14,12,16}};
        // 输出 [[15,13,2,5], [14,3,4,1], [12,6,8,9],[16,7,10,11]]

        rotate(matrixArr);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length - 1;
        // 错误，j 应该从0开始
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[n].length; j++) {
                int p = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = p;
            }
        }

        System.out.println(matrix.toString());

        for (int i = 0; i < matrix.length; i++) {
            reverse(matrix[i]);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }

    public static void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int p = arr[i];
            arr[i] = arr[j];
            arr[j] = p;
            i++;
            j--;
        }
    }


    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] row :
                matrix) {
            reverse(row);
        }

        System.out.println(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }

    public static void reverse1(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (j > i) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }


    // 矩阵的螺旋遍历 leetcode：54
    // 思路：按照右、下、左、上的顺序遍历数组，并使用四个变量圈定未遍历元素的边界
    // matrix = [[1,2,3,4], [5,6,7,8], [9, 10, 11, 12]]
    // 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int upper_bound = 0, lower_bound = m -1;
        int left_bound = 0, right_bound = n -1;
        List<Integer> res = new LinkedList<>();

        while (res.size() < m * n) { // 遍历完整个数组
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int i = left_bound; i < right_bound; i++) {
                    res.add(matrix[upper_bound][i]);
                }
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <= lower_bound; i++) {
                    res.add(matrix[i][right_bound]);
                }
                // 右边界左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // 底部从右向左遍历
                for (int i = right_bound; i < left_bound; i--) {
                    res.add(matrix[lower_bound][i]);
                }
                // 下边界上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = lower_bound; i >= upper_bound; i--) {
                    res.add(matrix[i][left_bound]);
                }
                left_bound++;
            }
        }
        return res;
    }
}
