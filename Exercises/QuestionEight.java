package Exercises;
//锁的对象
class Question {
    public synchronized static void synchronizedMethod1() {
        System.out.println("begin calling synchronizedMethod1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish calling synchronizedMethod1");
    }

    public synchronized static void synchronizedMethod2() {
        System.out.println("begin calling synchronizedMethod2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish calling synchronizedMethod2");
    }

    public synchronized void generalMethod1() {
        System.out.println("begin calling generalMethod1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish calling generalMethod1");
    }

    public synchronized void generalMethod2() {
        System.out.println("begin calling generalMethod2");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finish calling generalMethod2");
    }
}

public class QuestionEight {
    private static final Question q = new Question();
    public static void main(String[] args) {
        new Thread(q::generalMethod1).start();
        new Thread(q::generalMethod2).start();
//        new Thread(Question::synchronizedMethod1).start();
//        new Thread(Question::synchronizedMethod2).start();
    }
}
