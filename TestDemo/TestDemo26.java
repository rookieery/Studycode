package TestDemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
//线程池
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
        for (int i = 0; i < futureList.size(); i++) {
            try {
                System.out.println(futureList.get(i).get());//需要使用future的get方法来获取储存的内容
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //List<Runnable> runnables = executor.shutdownNow();
//        executor.shutdown();
//        //System.out.println(runnables.size());
//        System.out.println(executor.isShutdown());
//        System.out.println(executor.isTerminated());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(executor.isTerminated());
        //1.循环
//        while (executor.isTerminated()) {
//            break;
//        }
        //2.定时检查
    }
    private static void code3() {
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0;; i++) {
//            try {
//                Thread.sleep(500);//睡一会也都变成顺序线程了
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " " + LocalDateTime.now().toString());
//                }
//            });
//        }
        //固定延迟执行任务
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern
                                ("yyyy-MM-dd " + "HH:mm:ss")));
            }
        }, 0L, 2L, TimeUnit.SECONDS);
        //在某个时间点执行任务
        System.out.println(Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd " +
                "HH:mm:ss")));

        service.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd " +
                    "HH:mm:ss")));
        }, 2L, TimeUnit.SECONDS);
    }
    public static void main(String[] args) {
        code2();
    }
}
