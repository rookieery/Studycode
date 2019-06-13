package TestDemo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestDemo26 {
    private static void code1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 10, 1, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    AtomicInteger id = new AtomicInteger();
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("线程-" + id.getAndIncrement());
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 50; i++) {
            final int tmp = i;//匿名内部类的局部变量必须被final修饰
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + tmp);
                }
            });
        }
    }
    private static void code2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 10, 1, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(),
                new ThreadFactory() {
                    AtomicInteger id = new AtomicInteger();
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("线程-" + id.getAndIncrement());
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int tmp = i;
           final Future<String> future = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(new Random().nextInt(500));//0-5
                    return "任务编号：" + tmp + " 时间：" + LocalDateTime.now().toString();
                }
            });
            futureList.add(future);
        }
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (Future aFutureList : futureList) {
//            System.out.println(aFutureList);
//        }
        //List<Runnable> runnables = executor.shutdownNow();
        executor.shutdown();
        //System.out.println(runnables.size());
        System.out.println(executor.isShutdown());
        System.out.println(executor.isTerminated());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executor.isTerminated());
        //1.循环
//        while (executor.isTerminated()) {
//            break;
//        }
        //2.定时检查
    }

    public static void main(String[] args) {
        code2();
    }
}
