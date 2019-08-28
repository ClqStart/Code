package Practice;

import java.util.concurrent.*;

public class MyThreadPooolDeam1 {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1l,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());   //超过 3+5直接报错
//                new ThreadPoolExecutor.CallerRunsPolicy();  //超过回退调用上一个函数
//                new ThreadPoolExecutor.DiscardOldestPolicy();//抛弃等待太久的任务
//                new ThreadPoolExecutor.DiscardPolicy();// 直接丢包
        try {
            for (int i = 0; i < 8; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
