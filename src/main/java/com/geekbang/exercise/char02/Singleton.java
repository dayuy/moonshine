package com.geekbang.exercise.char02;

public class Singleton {
    public static void main(String[] args) {
        // 通过方法获得对象
        Singleton01 instance = Singleton01.getInstance();
        System.out.println(instance);
        Singleton01 instance2 = Singleton01.getInstance();
        System.out.println(instance2);

        System.out.println(Singleton02.n1);

        BBB bbb = new BBB();
        bbb.calculateTime();
    }
}

class Singleton01 {
    private String name;
    // 1、将构造器私有化，禁止被直接 new
    private Singleton01(String name) {
        this.name = name;
    }

    // 2、类的内部创建对象
    private static Singleton01 instance = new Singleton01("小红");

    // 3、向外暴露一个静态的公共方法。
    public static Singleton01 getInstance(){
        return instance;
    }
}

class Singleton02 {
    private String name;
    public static  int n1 = 999;
    // 1、将构造器私有化，禁止被直接 new
    private Singleton02(String name) {
        System.out.println("构造器调用。。。");
        this.name = name;
    }

    // 2、初始化内部创建对象变量
    private static Singleton02 cat;

    // 3、向外暴露一个静态的公共方法。只有调用此方法，才会创建对象。而不是类加载
    public static Singleton02 getInstance(){
        if (cat == null) {
            cat = new Singleton02("大橘");
        }
        return cat;
    }
}

abstract class Template {
    abstract public void job();
    public void calculateTime() {
        long start = System.currentTimeMillis();
        job();
        long end = System.currentTimeMillis();
        System.out.println("执行时间: " + (end - start));
    }
}

class BBB extends Template {
    @Override
    public void job() {
        long sum = 0;
        for (int i = 0; i < 2000; i++) {
            sum++;
        }
    }
}

