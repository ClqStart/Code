package config;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestConfig {

    ZooKeeper zk;


    @Before
    public void conn() {

        zk = ZKUtills.getZk();


    }

    @After
    public void close() {

        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getConf() throws InterruptedException {
        // confTest节点为根节点
        WatchCallback watchCallback = new WatchCallback();
        watchCallback.setZk(zk);
        MyConf myConf = new MyConf();
        watchCallback.setMyConf(myConf);


        watchCallback.aWait();


        while (true) {
            if (myConf.getConf().equals("")) {
                System.out.println("diu le ...");
                watchCallback.aWait();
            } else {
                System.out.println(myConf.getConf());
            }
            Thread.sleep(3000);
        }

    }


}

