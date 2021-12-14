package com.geekbang.oldstyle;


/**
 * 原地旋转矩阵：
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
                
    }



}
