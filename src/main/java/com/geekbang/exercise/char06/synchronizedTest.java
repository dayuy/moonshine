package com.geekbang.exercise.char06;

public class synchronizedTest {
    public static void main(String[] args) {
        SellTicket03 sellTicket03 = new SellTicket03();

        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
        new Thread(sellTicket03).start();
    }
}

class SellTicket03 implements Runnable {
    private int ticketNum = 50; // 让多个线程共享ticketNum
    private boolean loop = true;

    // 1、public synchronized void sell(){} 就是一个同步方法
    // 2、这时锁在 this 对象上
    public synchronized void sell() { // 同步方法，同一时刻，只能有一个线程来执行sell方法
        if (ticketNum <= 0) {
            System.out.println("售票结束。。。");
            loop = false;
            return;
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票，剩余票数" + (--ticketNum));
    }
    // 3、也可以在代码块上写 synchronize, 同步代码块。互斥锁还是在this上
    //    ！！要求每个线程的锁对象为同一个，不然锁不住 (this、object)都是同一个对象
    private Object object = new Object();
    public void sell_1() {
        // synchronized (this) {
        synchronized (object) {
            if (ticketNum <= 0) {
                System.out.println("售票结束。。。");
                loop = false;
                return;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票，剩余票数" + (--ticketNum));
        }
    }
    // 4、同步方法（静态的）锁为当前类本身
    // 1> public synchronized static void m1() {} 锁是加在 SellTicket03.class
    public synchronized static void m1() {}
    // 2> 如果在静态方法中实现一个同步代码块. 对象则是类名.class
    public static void m2() {
        synchronized (SellTicket03.class) {
            System.out.println("m2");
        }
    }
    @Override
    public void run() {
        while (loop) {
            sell_1();
        }
    }
}
