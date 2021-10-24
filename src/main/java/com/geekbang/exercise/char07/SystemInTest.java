package com.geekbang.exercise.char07;

public class SystemInTest {
    public static void main(String[] args) {
        /**
         * System.in 标准输入 键盘
         *  1、public final static InputStream in = null;
         *  2、System.in 编译类型 InputStream
         *  3、System.in 运行类型 BufferedInputStream
         * **/
        System.out.println(System.in.getClass());

        /**
         * System.out 标准输出 显示器
         * 1、public final static PrintStream out = null
         * 2、编译类型 PrintStream
         * 3、运行类型 PrintStream
         * **/
        System.out.println(System.out.getClass());
    }
}
