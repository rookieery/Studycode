package TestDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyTickThread extends Thread {

    private int tick = 10;

    private String title;

    public MyTickThread(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        while (this.tick > 0) {
            System.out.println(this.title + "买了一张票，剩余" + (--this.tick));
        }
    }
}

class MyTickCallable implements Callable<String> {

    private int tick = 10;

    @Override
    public String call() {
        while (this.tick > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Thread.currentThread().getName() 那个线程在执行代码 ，返回线程的名字
            synchronized (this) {
                if (this.tick > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + (--this.tick));
                }
            }
        }
        return "票卖光了";
    }
}

class MyTickRunnable implements Runnable {

    private int tick = 10;

    @Override
    public void run() {
        while (this.tick > 0) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Thread.currentThread().getName() 那个线程在执行代码 ，返回线程的名字
            synchronized (this) {
                if (this.tick > 0) {
                    System.out.println(Thread.currentThread().getName() + "买了一张票，剩余" + (--this.tick));
                }

            }
        }

    }
}

public class TestDemo21 {
    public static void main(String[] args) {
//        Thread thread1 = new MyTickThread("黄牛A");
//        Thread thread2 = new MyTickThread("黄牛B");
//        thread1.start();
//        thread2.start();
        //多线程共享，线程安全性，并发修改问题
        Runnable runnable = new MyTickRunnable();
        Thread thread1 = new Thread(runnable,"黄牛A");
        Thread thread2 = new Thread(runnable,"黄牛B");
        thread1.start();
        thread2.start();
//        Callable<String> runnable = new MyTickCallable();
//        FutureTask<String> futureTask1 = new FutureTask<>(runnable);
//        FutureTask<String> futureTask2 = new FutureTask<>(runnable);
//        Thread thread1 = new Thread(futureTask1);
//        Thread thread2 = new Thread(futureTask2);
//        thread1.start();
//        thread2.start();
//        try {
//            System.out.println(futureTask.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
