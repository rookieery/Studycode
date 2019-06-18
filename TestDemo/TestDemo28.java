package TestDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//单例模式
//饿汉式单例模式
class Singleton1 {
    //此处static是为了能够让getInstance()方法的返回值对象直接通过类名调用
    private final static Singleton1 instance = new Singleton1();

    private Singleton1() {

    }

    //此处static是为了能够让外部类通过类名直接调用此方法
    public static Singleton1 getInstance() {
        return Singleton1.instance;
    }

    public void print() {
        System.out.println("This is only a instance!");
    }
}

//静态内部类形式的饿汉式单例模式
class Singleton2 {
    private static class inner {
        final static Singleton2 instance = new Singleton2();
    }

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return inner.instance;
    }

    public void print() {
        System.out.println("This is only a instance!");
    }
}

class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

    public void print() {
        System.out.println("This is only a instance!");
    }
}

class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }

    public void print() {
        System.out.println("This is only a instance!");
    }
}

class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        synchronized (Singleton5.class) {
            if (instance == null) {
                instance = new Singleton5();
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("This is only a instance!");
    }
}

class Singleton6 {
    private static Singleton6 instance;
    private static Lock instanceLock = new ReentrantLock();

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        instanceLock.lock();
        if (instance == null) {
            instance = new Singleton6();
        }
        instanceLock.unlock();
        return instance;
    }

    public void print() {
        System.out.println("This is only a instance!");
    }
}

class RunnableTest implements Runnable {

    @Override
    public void run() {
        System.out.println(Singleton6.getInstance().hashCode());
    }
}

public class TestDemo28 {
    public static void main(String[] args) {
        Runnable runnable = new RunnableTest();
        new Thread(runnable, "t1").start();
        new Thread(runnable, "t2").start();
        new Thread(runnable, "t3").start();
        new Thread(runnable, "t4").start();
    }
}
