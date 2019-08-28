package Practice;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        List list = null;
        System.out.println(Integer.MAX_VALUE);

        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //检查开头
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("b"));

        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        //阻塞等待
        blockingQueue.put("a");
        blockingQueue.put("d");
        blockingQueue.put("c");
        //blockingQueue.put("x");
        //阻塞等待获取
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
       // blockingQueue.take();

        //时间设置放入获取等待
        blockingQueue.offer("a",2l, TimeUnit.SECONDS);
        blockingQueue.offer("b",2l, TimeUnit.SECONDS);
        blockingQueue.offer("c",2l,TimeUnit.SECONDS);

        blockingQueue.poll(2l,TimeUnit.SECONDS);
        blockingQueue.poll(2l,TimeUnit.SECONDS);
        blockingQueue.poll(2l,TimeUnit.SECONDS);

    }

}
