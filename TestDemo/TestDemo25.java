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

final class ProducerConsumerLauncher {

    private ProducerConsumerLauncher() {

    }

    public static void run(int producers, int consumers) {
        System.out.println("生产者：" + producers + " 消费者：" + consumers);
        Queue<Goods> queue = new LinkedList<>();
        Runnable produce = new Producer(queue);
        Runnable consumer = new Consumer(queue);
        //生产者线程
        for (int i = 0; i < producers; i++) {
            new Thread(produce, "生产者-" + i).start();
        }
        //消费者线程
        for (int i = 0; i < consumers; i++) {
            new Thread(consumer, "消费者-" + i).start();
        }

    }
}

public class TestDemo25 {
    public static void code1() {
        //生产者
        //消费者
        //容器
        Queue<Goods> queue = new LinkedList<>();
        Runnable produce = new Producer(queue);
        Runnable consumer = new Consumer(queue);

        //生产者线程
        for (int i = 0; i < 5; i++) {
            new Thread(produce, "生产者-" + i).start();
        }

        //消费者线程
        for (int i = 0; i < 2; i++) {
            new Thread(consumer, "消费者-" + i).start();
        }
    }

    public static void main(String[] args) {
        //可配置
        //1.scanner键盘输入
        //2.file
        //3.properties(key=value)
        //4.command line arguments 命令行参数  java Main.class  a b c
        //5.数据库  mysql (JDBC)
        //6.System.env("")
        int defaultProducers = 5;
        int defaultConsumers = 5;
        int producers = defaultProducers;
        int consumers = defaultConsumers;
        if (args.length == 1) {
            try {
                producers = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                producers = defaultProducers;
            }
        }
        if (args.length == 2) {
            try {
                producers = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                producers = defaultProducers;
            }
            try {
                consumers = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                consumers = defaultConsumers;
            }
        }
        if (producers <= 0) {
            producers = defaultProducers;//开发者，需求
        }
        if (consumers <= 0) {
            consumers = defaultConsumers;
        }
        ProducerConsumerLauncher.run(producers, consumers);
    }
}
