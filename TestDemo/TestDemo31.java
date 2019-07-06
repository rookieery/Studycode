package TestDemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//生产者消费者模型--Lock+Condition
class Commodities {
    private String name;
    private String id;
    private Double price;

    public Commodities(String name, String id, Double price) {
        this.name = name;
        this.id = id;
        this.price = price;
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
        return "Commodities{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", price=" + price +
                '}';
    }
}

class Producer2 implements Runnable {
    private final Queue<Commodities> queue;
    private final Lock lock;
    private final Condition condition;

    public Producer2(Queue<Commodities> queue, Lock lock, Condition condition) {
        this.queue = queue;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                lock.lock();
                if (queue.size() >= 3) {
                    System.out.println(Thread.currentThread().getName() + "容器已满，停止生产");
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String id = UUID.randomUUID().toString();
                    String name = "ice-cream";
                    Double price = new Random().nextDouble();
                    Commodities commodities = new Commodities(id, name, price);
                    queue.add(commodities);
                    System.out.println(Thread.currentThread().getName() + "生产了一个" + commodities);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

class Consumer2 implements Runnable {
    private final Queue<Commodities> queue;
    private final Lock lock ;
    private final Condition condition ;

    public Consumer2(Queue<Commodities> queue, Lock lock, Condition condition) {
        this.queue = queue;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                lock.lock();
                if (queue.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + "容器已空，开始生产");
                    condition.signalAll();
                } else {
                    Commodities commodities = this.queue.poll();
                    System.out.println(Thread.currentThread().getName() + "消费了一个" + commodities);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

public class TestDemo31 {
    public static void main(String[] args) {
        Queue<Commodities> queue = new LinkedList<>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Runnable producer = new Producer2(queue,lock,condition);
        Runnable consumer = new Consumer2(queue,lock,condition);
        for (int i = 0; i < 5; i++) {
            new Thread(producer, "生产者" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(consumer, "消费者" + i).start();
        }
    }
}
