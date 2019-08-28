package Practice;

/*
* 1.runnable;
* 2.callable; 有返回值，会抛异常
* 2.实现callable接口
* 4.线程池
* */
import javax.naming.spi.StateFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDeam {
    public static void main(String[] args) {
       // ExecutorService threadPool = Executors.newFixedThreadPool(5);
       // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();

            try{
                for (int i = 0; i < 10; i++) {
                    threadPool.execute(() ->{
                        System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                    });
                    //try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                threadPool.shutdown();
        }



    }
}
