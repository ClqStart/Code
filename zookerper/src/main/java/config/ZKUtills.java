package config;


import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZKUtills {

    private static ZooKeeper zk;

    private static String address = "192.168.4.105:2181,192.168.4.106:2181,192.168.4.107:2181,192.168.4.108:2181/testConf";

    private static DefaultWatch watch = new DefaultWatch();

    private static CountDownLatch init = new CountDownLatch(1);

    public static ZooKeeper getZk() {
        try {
            zk = new ZooKeeper(address, 1000, watch);
            watch.setCd(init);
            try {
                init.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return zk;
    }


}
