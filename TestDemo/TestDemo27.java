package TestDemo;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
//生产者与消费者模型--BlockingQueue
class Goods1 {
    private String name;
    private String id;
    private Double price;

    public Goods1(String name, String id, Double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", price=" + price +
                '}';
    }
}

//class Producer1 extends Thread {
//    private BlockingQueue<Goods1> queue;
//
//    public Producer1(BlockingQueue<Goods1> queue) {
//        this.queue = queue;
//    }
//
//    public void run() {
//        String id = UUID.randomUUID().toString();
//        String name = "包子";
//        Double price = new Random().nextDouble();
//        Goods1 goods = new Goods1(id, name, price);
//        while (true) {
//            System.out.println(getName() + "生产者准备生产集合元素！");
//            try {
//                Thread.sleep(500);
//                queue.put(goods);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(getName() + "生产完成：" + queue);
//        }
//    }
//}
//
//class Consumer1 extends Thread {
//    private BlockingQueue<Goods1> queue;
//
//    public Consumer1(BlockingQueue<Goods1> queue) {
//        this.queue = queue;
//    }
//
//    public void run() {
//        while (true) {
//            System.out.println(getName() + "消费者准备消费集合元素！");
//            try {
//                Thread.sleep(500);
//                queue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(getName() + "消费完成：" + queue);
//        }
//    }
//}
class Producer1 implements Runnable {
    private BlockingQueue<Goods1> queue;

    public Producer1(BlockingQueue<Goods1> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String id = UUID.randomUUID().toString();
        String name = "包子";
        Double price = new Random().nextDouble();
        Goods1 goods = new Goods1(id, name, price);
        while (true) {
            System.out.println(Thread.currentThread().getName() + "准备生产！");
            try {
                Thread.sleep(500);
                queue.put(goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "生产完成：" + goods);
        }
    }
}

class Consumer1 implements Runnable {
    private BlockingQueue<Goods1> queue;

    public Consumer1(BlockingQueue<Goods1> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "准备消费！");
            try {
                Thread.sleep(500);
                Goods1 goods = queue.take();
                System.out.println(Thread.currentThread().getName() + "消费完成：" + goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestDemo27 {
    public static void main(String[] args) {
        BlockingQueue<Goods1> queue = new ArrayBlockingQueue<>(3);
        Runnable produce = new Producer1(queue);
        Runnable consumer = new Consumer1(queue);
        //生产者线程
        for (int i = 0; i < 3; i++) {
            new Thread(produce, "生产者-" + i).start();
        }
        //消费者线程
        for (int i = 0; i < 2; i++) {
            new Thread(consumer, "消费者-" + i).start();
        }
    }
}

//        BlockingQueue<Goods1> queue = new ArrayBlockingQueue<>(2);
//        new Producer1(queue).start();
//        new Producer1(queue).start();
//        new Producer1(queue).start();
//        new Consumer1(queue).start();
//        new Consumer1(queue).start();