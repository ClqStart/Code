package lock;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLock {

    ZooKeeper zk;

    @Before
    public  void  conn (){
        zk = ZKUtills.getZk();
    }

    @After
    public void close(){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lock(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    WatchCallBack callBack = new WatchCallBack();
                    callBack.setZk(zk);
                    // 每一个锁
                    String treadName = Thread.currentThread().getName();
                    callBack.setTreadName(treadName);

                    //抢锁
                    callBack.tryLock();

                    //干活
                    System.out.println("ganhuo");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //释放锁

                    callBack.unlock();

                }
            }.start();
        }
        while (true){

        }
    }
}
