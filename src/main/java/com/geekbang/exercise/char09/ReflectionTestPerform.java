package com.geekbang.exercise.char09;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 测试反射使用和普通调用的性能
public class ReflectionTestPerform {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        m1();
        m2();
        m3();
    }

    public static void m1() {
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统调用方法耗时：" + (end - start));
    }

    public static void m2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class cls = Class.forName("com.geekbang.exercise.char09.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2 耗时：" + (end - start));
    }

    // 反射调用优化：关闭访问检查
    public static void m3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class cls = Class.forName("com.geekbang.exercise.char09.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        hi.setAccessible(true); // 在反射调用方法时，取消访问检查
        long start = System.currentTimeMillis();
        for (int i = 0; i < 90000000; i++) {
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m3 耗时：" + (end - start));
    }
}
