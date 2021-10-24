package com.geekbang.exercise.char02;

public class ChildGame {
    public static int staticCount = 0;
    public int age = 20;
    public static void main(String[] args) {
//        int count = 0;

        Child child1 = new Child("白骨精");
        child1.join();
//        count++;
        child1.count++;

        Child child2 = new Child("狐狸精");
        child2.join();
//        count++;
        child2.count++;


        // 类变量，可以通过类名来访问
        System.out.println("共有" + Child.count + "加入了游戏！");
        System.out.println("child1.count:" + child1.count);
        System.out.println("child2.count:" + child2.count);



        // 静态方法
        Stu tom = new Stu("tom");
        Stu mary = new Stu("mary");

        tom.payFee(100);
        mary.payFee(200);

        Stu.showFee();


        // main 里不能直接调用非静态
        ChildGame childGame = new ChildGame();
        System.out.println(childGame.age);
        // 可以直接调用静态方法
        System.out.println(staticCount);


        for (var i = 0; i<args.length;i++) {
            System.out.println(args[i]);
        }
    }

}

class Child {
    private String name;

    // 1. 将 count 作为所有对象共享的变量
    // 定义一个变量 count,是一个类变量(静态变量) static 静态
    // 该变量特点：是会被Child类的所有对象实例共享
    public static int count = 0;

    public Child(String name) {
        this.name = name;
    }

    public void join() {
        System.out.println(name + "加入游戏！");
    }
}


/**
 * 问题分析：
 * 1、count 是一个独立于对象的变量。
 * 2、以后访问 count 很麻烦，没有使用到 OOP
 * 3、所以，引出 类变量/静态变量
 * **/


// 静态方法
class Stu {
    private String name;

    private  static double fee = 0;

    public Stu(String name) {
        this.name = name;
    }

    // 1. 静态方法就可以访问静态属性/变量
    public static void payFee(double fee) {
        Stu.fee += fee;
    }

    public static void showFee() {
        System.out.println(fee);
//        System.out.println(this.fee);
//        System.out.println(super.fee);
//        System.out.println(Stu.name);
    }
}
