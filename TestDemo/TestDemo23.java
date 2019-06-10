package TestDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Lock
class MyThread1 implements Runnable {
    private int ticket = 20;
    private Lock ticketLock = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketLock.lock();
            try {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + --this.ticket);
                }
            } finally {
                ticketLock.unlock();
            }
        }
    }
}

//同步对象
class MyThread2 implements Runnable {
    private int ticket = 20;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + --this.ticket);
                }
            }
        }
    }
}

//同步对象
class MyThread3 implements Runnable {
    private int ticket = 20;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.buy();
        }
    }

    private synchronized void buy() {
        if (this.ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + --this.ticket);
        }
    }
}

//同步Class对象
class MyThread4 implements Runnable {
    private int ticket = 20;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (MyThread4.class) {
                if (this.ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + --this.ticket);
                }
            }
        }
    }
}

//同步普通对象
class MyThread5 implements Runnable {
    private Integer ticket = 20;
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ticket) {
                if (this.ticket> 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + --ticket);
                }
            }
        }
    }
}

public class TestDemo23 {
    public static void main(String[] args) {
        MyThread5 myThread1 = new MyThread5();
        Thread thread1 = new Thread(myThread1, "黄牛A");
        Thread thread2 = new Thread(myThread1, "黄牛B");
        thread1.start();
        thread2.start();
    }
}
