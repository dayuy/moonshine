package com.geekbang.exercise.char06;

public class ThreadTest {
    // 查看线程执行情况 Terminal内，$ Jconsole
    public static void main(String[] args) throws InterruptedException {
        // 创建Cat对象，当线程使用
        Cat cat = new Cat();
        /**
         * 1、public synchronized void start() { start0(); }
         * 2、private native void start0()
         *   start0() 是本地方法，由JVM调用，底层是c/c++实现。真正实现多线程的效果，是start0(),而不是run()
         * **/
        cat.setDaemon(true); // 设置为守护线程，当主线程结束后，子线程也同时结束
        cat.start(); // 启动子线程 Thread-0， 最终会执行cat的run方法
//        cat.run(); // 执行run方法完毕后，才会向下执行。而不是启动子线程，所以会塞到main线程里，而不是start开启一个子线程并发执行

        // ：当main线程启动一个子线程 Thread-0，主线程不会阻塞，会继续执行
        // main线程和Thread-0交替执行
        // 结论： 主线程结束，子线程不一定结束。所以线程结束后，这个进程才会销毁
        System.out.println("主线程继续执行" + Thread.currentThread().getName()); // 名字main
        for (int i = 0; i < 6; i++) {
            System.out.println("主线程 i= " + i);
            Thread.sleep(1000);

            if (i == 3) {
//                cat.join(); // 线程插队（一定会成功），让cat线程先执行完毕，再接着执行主线程
//                Thread.yield(); // 礼让，让其他线程先执行完。（不一定会成功）
            }
        }
    }
}
// 1、当一个类继承了 Thread 类，该类就可以当作线程使用
// 2、run Thread类 实现了 Runnable 接口的run方法
class Cat extends Thread {
    private int times = 0;
    @Override
    public void run() {
        while (true) {
            super.run();
            System.out.println("miaomiao, i am a cat" + ++times + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 8) {
                break;
            }
        }
    }
}

