package Exercises;

import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("1")) {
            testA();
        } else {
            testB();
        }
    }

    public synchronized void testA() {
        System.out.println("线程1在运行...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void testB() {
        System.out.println("线程2在运行...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class QuestionThree {
    public static void main(String[] args) {
        Task task = new Task();//对象锁(一个对象只有一个锁)
        Thread thread1 = new Thread(task,"1");
        Thread thread2 = new Thread(task,"2");
        thread1.start();
        thread2.start();
    }
}
