package TestDemo;

import java.util.concurrent.*;
//实现多线程的三种方式
class MyThread extends Thread {
    private String title;

    public MyThread(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.title + " " + i);
        }
    }
}

class MyRunnable implements Runnable {
    private String title;

    public MyRunnable(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}

class MyCallable implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
        }
        //Thread.sleep(1000);
        return sum;
    }
}

public class TestDemo20 {
    private static void code1() {
        //怎样操作使得线程按顺序进行？
        System.out.println("这是main方法的开始");
        MyThread myThread1 = new MyThread("MyThread1");
        myThread1.start();
        MyThread myThread2 = new MyThread("MyThread2");
        myThread2.start();
        MyThread myThread3 = new MyThread("MyThread3");
        //错误的启动
//        myThread1.run();
//        myThread2.run();
//        myThread3.run();
        myThread3.start();
        System.out.println("这是main方法的结束");
    }

    private static void code2() {
        System.out.println("这是main方法的开始");
        Runnable runnable1 = new MyRunnable("MyRunnable1");
        //用一个runnable1启动两个线程和分别用两个runnable启动两个线程有什么区别？没啥区别
        //Runnable runnable2 = new MyRunnable("MyRunnable2");
        Thread thread1 = new Thread(runnable1, "A");
        Thread thread2 = new Thread(runnable1, "B");
        thread1.start();
        thread2.start();
        System.out.println("这是main方法的结束");
    }

    private static void code3() {
        System.out.println("这是main方法的开始");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread1  " + i);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread2  " + i);
                }
            }
        });
        thread1.start();
        thread2.start();
        System.out.println("这是main方法的结束");
    }

    private static void code4() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }).start();
        new Thread(() -> System.out.println("Hello Java")).start();
    }

    private static void code5() {
        Callable<Integer> callable = new MyCallable();//面向接口编程
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            Integer sum = futureTask.get(1, TimeUnit.MILLISECONDS);
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        code1();
    }
}
