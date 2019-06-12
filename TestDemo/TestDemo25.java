package TestDemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
//生产者消费者问题
class Goods {
    private String name;
    private String id;
    private Double price;

    public Goods(String name, String id, Double price) {
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

class Producer implements Runnable {
    private final Queue<Goods> queue;

    public Producer(Queue<Goods> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.queue) {
                if (queue.size() >= 3) {
                    System.out.println(Thread.currentThread().getName() + "容器已满，停止生产");
                    try {
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String id = UUID.randomUUID().toString();
                    String name = "包子";
                    Double price = new Random().nextDouble();
                    Goods goods = new Goods(id, name, price);
                    queue.add(goods);
                    System.out.println(Thread.currentThread().getName() + "生产了一个" + goods);
                }
            }
        }
    }
}

class Consumer implements Runnable {
    Queue<Goods> queue;

    public Consumer(Queue<Goods> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.queue) {
                if (this.queue.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + "容器已空，开始生产");
                    this.queue.notifyAll();
                } else {
                    Goods goods = this.queue.poll();
                    System.out.println(Thread.currentThread().getName() + "消费了一个" + goods);
                }
            }
        }
    }
}

public class TestDemo25 {
    public static void main(String[] args) {
        Queue<Goods> queue = new LinkedList<>();
        Runnable producer = new Producer(queue);
        Runnable consumer = new Consumer(queue);
        for (int i = 0; i < 5; i++) {
            new Thread(producer,"生产者"+i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(consumer,"消费者"+i).start();
        }
    }
}
