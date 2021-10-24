package com.geekbang.exercise.char06;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();
        // dog.start(); // 这里没有start方法
        // 但是，这里可以创建 Thread 实例来调用start方法。「代理模式」
        Thread thread = new Thread(dog);
        System.out.println(thread.getName() + " 状态 " + thread.getState());
        thread.start();

        // 线程的生命周期状态
        while (Thread.State.TERMINATED != thread.getState()) {
            System.out.println(thread.getName() + " 状态 " + thread.getState());
            Thread.sleep(1000);
        }
        System.out.println(thread.getName() + " 状态 " + thread.getState());

        Tiger tiger = new Tiger();
        ThreadProxy threadProxy = new ThreadProxy(tiger);
        threadProxy.start();
    }
}
class Dog implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("小狗汪汪。。。" + ++count + Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 8) {
                break;
            }
        }
    }
}
// 线程代理类，模拟一个简单的Thread类
class ThreadProxy implements Runnable {
    private Runnable target = null;
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
    public ThreadProxy(Runnable target) {
        this.target = target;
    }
    public void start() {
        start0(); // 这个方法是真正实现多线程的方法
    }
    public void start0() {
        run();
    }
}
class Animal {}
class Tiger extends Animal implements Runnable {
    @Override
    public void run() {
        System.out.println("老虎咆哮。。。" + Thread.currentThread().getName());
    }
}