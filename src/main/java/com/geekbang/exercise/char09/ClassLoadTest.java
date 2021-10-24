package com.geekbang.exercise.char09;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.lang.*;

// 类的加载机制：动态加载、静态加载
@SuppressWarnings({"all"})
public class ClassLoadTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException {
        Scanner scanner = new Scanner(System.in);
        String k = scanner.next();
        switch (k) {
            case "1":
                Dog dog = new Dog(); // 静态加载，在javac ClassLoadTest.java时就报错
                dog.cry();
                break;
            case "2":
                Class<?> cls = Class.forName("Person"); // 动态加载 Person类，输入2使用时才会报错
                Object o = cls.newInstance();
                Method hi = cls.getMethod("hi");
                hi.invoke(o);
                break;
            default:
                System.out.println("nothing");
        }
    }
}

class Dog {
    static int age = 20;
    public void cry() {}
}

class A {
    // 类加载-连接阶段-准备：如何处理属性
    // 1) n1 是实例属性，不是静态变量，因此在准备阶段，是不会分配内存
    // 2）n2 是静态变量，分配内存 并 默认初始化为0，而不是20
    // 3）n3 是常量，一旦赋值就不变，n3 = 30
    public int n1 = 10;
    public static int n2 = 20;
    public static final int n3 = 30;
    // 类加载-连接阶段-解析：
    //      虚拟机将常量池内的符号引用替换为直接引用的过程 A->A.class 0x1111

    public void t1() {
        // 类加载-初始化阶段
        System.out.println(Dog.age);
        // 1、加载Dog类，并生成Dog的class对象
        // 2、Linking阶段 age = 0
        // 3、初始化阶段
        //      依次自动收集类中的所有静态变量的赋值操作和静态代码块中的语句，并合并
        /**
         * clinit(){
         *     System.out.printlb("Dog 静态代码块被执行");
         *     age = 300;
         *     age = 100;
         * }
         * 合并 age = 100;
         * **/
        // 4、加载类的时候，是有同步锁机制的
        //      所有保证了，一个类在内存中，只有一份Class对象（类只加载一次）

    }

}


























