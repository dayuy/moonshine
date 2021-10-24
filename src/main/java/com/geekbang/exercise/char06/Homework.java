package com.geekbang.exercise.char06;

public class Homework {
    public static void main(String[] args) {
        WithdrawCatch withdrawCatch = new WithdrawCatch();
        Thread t1 = new Thread(withdrawCatch);
        Thread t2 = new Thread(withdrawCatch);
        Thread t3 = new Thread(withdrawCatch);
        t1.start();
        t2.start();
        t3.start();
    }
}

class WithdrawCatch implements Runnable {
    private int deposit = 8000;

    @Override
    public void run() {
        while (true) {
            // 1、使用 synchronized 实现了线程同步
            // 2、当多个线程执行到这里，会去争夺 this 对象锁
            // 3、哪个对象争夺到this对象锁，就执行 synchronized 代码块，执行完成后 会释放this对象锁
            // 4、争夺不到的线程，就处在Blocked，准备继续争夺
            synchronized (this) {
                if (deposit < 1000) {
                    System.out.println("余额不足");
                    break;
                }

                deposit -= 1000;
                System.out.println(Thread.currentThread().getName() + "取钱 - 1000" + "余额：" + deposit);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
