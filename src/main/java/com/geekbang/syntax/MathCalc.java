package com.geekbang.syntax;

public class   MathCalc {
    public static void main(String[] args) {
        System.out.println(5+6);
        System.out.println(5-6);
        System.out.println(5*6);
        System.out.println(5/6.0);
        System.out.println((1+2+3)*4/5.0);

        double a= 10/3.0;
        System.out.println(a);

        byte byteVar = 100;
        System.out.println(byteVar);

        short shortVar = 30000;
        System.out.println(shortVar);

        int intVar = 1000000000;
        System.out.println(intVar);

        long longVar = 9223373036808434L;
        System.out.println(longVar);

        float floatVar = 100.00000000666F;
        System.out.println(floatVar);

        double doubleVar = 100.00000000666;
        System.out.println(doubleVar);

        boolean booleanVar = true;
        System.out.println(booleanVar);

        char charVar = 'a';
        System.out.println(charVar);

        int b = 10;
        int c = 3;
        int d = b / c;
        System.out.println(d);


        int e = 05;
        int f = 011;
        int g = 0xF;
        int h = 0x11;
        System.out.printf("%d, %d, %d, %d", e, f, g, h); // 5, 9, 15, 17
    }
}

// bit 和 byte
// 1、 一个二进制的位叫做一个bit。1010 是4bit
// 2、 八个二进制的位，组成一个byte。硬盘的存储单位，都是byte。byte是计算机基本的衡量存储单位


// 数字基本类型
// 1、整数类型
//    byte 占用1个byte，值域是 -128～127
//    short 占用2个byte，值域是 -32768 ～ 32767
//    int 占用4个byte，值域是 -2147483648 ～ 2147483647  （整数缺省值类型是int）
//    long 占用8个byte，值域是 -9223373036854774808 ～ 9223372036854774807
// 2、浮点类型
//    float 占用4个byte，有精度
//    double 精度数float的两倍，占用8个byte。（浮点数缺省值是double）
// 3、布尔和字符数据类型
//    boolean 占用1个byte，值域是true，false
//    char 占用2个byte，值域是所有字符


// 字面值的八进制和十六进制
// 1、以 0 开头的整数为八进制
//      05 就是十进制的 5
//      011 就是十进制的 9
// 2、以 0x 开头的整数为十六进制
//      0xF 就是十进制的 15
//      0x11 就是十进制的 17


// 按位运算符 针对二进制的
//  按位并 &
//  按位或 ｜
//  按位异或  ^
//  按位取反  ～


