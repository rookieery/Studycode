package TestDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

//死锁与ThreadLocal
class Pen {
    private String name = "笔";

    public String getName() {
        return name;
    }
}

class Book {
    private String name = "本";

    public String getName() {
        return name;
    }
}

class ThreadA implements Runnable {

    private final Pen pen;
    private final Book book;

    ThreadA(Pen pen, Book book) {
        this.pen = pen;
        this.book = book;
    }

    @Override
    public void run() {
        synchronized (this.pen) {
            System.out.println(Thread.currentThread().getName() + " 有笔，缺个本");
            synchronized (this.book) {
                System.out.println(Thread.currentThread().getName() + " 有笔，有本");
            }
        }

    }
}

class ThreadB implements Runnable {

    private final Pen pen;
    private final Book book;

    ThreadB(Pen pen, Book book) {
        this.pen = pen;
        this.book = book;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (this.book) {
            System.out.println(Thread.currentThread().getName() + " 有本，缺个笔");
            synchronized (this.pen) {
                System.out.println(Thread.currentThread().getName() + " 有本，有笔");
            }
        }
    }
}

class MyRunnableThreadLocal implements Runnable {

    private final ThreadLocal threadLocal;

    MyRunnableThreadLocal(ThreadLocal threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        this.threadLocal.get();
        System.out.println(this.threadLocal.get());
        this.threadLocal.set("Hello");
        System.out.println(this.threadLocal.get());
    }
}

public class TestDemo24 {
    private static String commonStr;
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static List<String> list = new ArrayList<>();

    //用Thread类和用Runnable接口效果一样
    private static void code1() {
        final Book book = new Book();
        final Pen pen = new Pen();
        ThreadA runnableA = new ThreadA(pen, book);
        Thread threadA = new Thread(runnableA);
        threadA.setName("Thread-A");
        ThreadB runnableB = new ThreadB(pen, book);
        Thread threadB = new Thread(runnableB);
        threadB.setName("Thread-B");
        threadA.start();
        threadB.start();
    }

    private static void code2() {
        commonStr = "main";
        threadLocal.set("main-thread-value");
        new Thread(new Runnable() {
            @Override
            public void run() {
                commonStr = "threadA";//此原变量可以更改
                threadLocal.set("A-thread-value");//此原变量拷贝成了副本，可以更改原变量
                System.out.println(Thread.currentThread().getName() + "  commonStr = " + commonStr);
                System.out.println(Thread.currentThread().getName() + "  threadLocal = " + threadLocal.get());
            }
        }, "Thread-A").start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Thread.currentThread().getName() + "  commonStr = " + commonStr);
        System.out.println(Thread.currentThread().getName() + "  threadLocal = " + threadLocal.get());
    }

    private static void code3() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        });
//        new Thread(() -> System.out.println(threadLocal.get())).start();
//        new Thread(() -> System.out.println(threadLocal.get())).start();

//        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "hello");//lambda
        //ThreadLocal<String> threadLocal = ThreadLocal.withInitial("hello" :: toUpperCase);// 方法引用
//        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(new Supplier<String>() {//匿名类的实例化对象
//            @Override
//            public String get() {
//                return "hello";
//            }
//        });
        new Thread(new MyRunnableThreadLocal(threadLocal)).start();
    }
    public static void main(String[] args) {
        code3();
    }
}
