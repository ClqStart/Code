import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Api {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        System.out.println("Hello word");
        //zk有session的概念，但是没有线程池的概念。因为每一个连接会得到一个独立的session，监控watch时就会出现混乱。

        /**
         * 在new zk的时候注册session级别的watch是异步的，
         * 如果想要成功连接后再查看zk状态，需要先阻塞。
         */

        final CountDownLatch downLatch = new CountDownLatch(1);
        final ZooKeeper zk = new ZooKeeper("192.168.4.105:2181,192.168.4.106:2181,192.168.4.107:2181,192.168.4.108:2181", 3000, new Watcher() {
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                Event.EventType type = event.getType();
                String path = event.getPath();
                System.out.println("###############");
                System.out.println(event.toString());
                System.out.println("路径为:" + path);

                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        System.out.println("connected");
                        downLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                }
                switch (type) {

                    case None:
                        break;
                    case NodeCreated:
                        break;
                    case NodeDeleted:
                        break;
                    case NodeDataChanged:
                        break;
                    case NodeChildrenChanged:
                        break;
                }
            }
        });
        downLatch.await();
        ZooKeeper.States state = zk.getState();
        switch (state){
            case CONNECTING:
                System.out.println("ing....");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed..........");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                break;
            case AUTH_FAILED:
                break;
            case NOT_CONNECTED:
                break;
        }
        String pathName = zk.create("/ooxx", "olddata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        final Stat stat = new Stat();
        byte[] node = zk.getData("/ooxx", new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("getData watch: " + event.toString());
                try {//wach: this, 当前，true 默认zk注册的
                    zk.getData("/ooxx",this,stat);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);


        System.out.println(new String(node));
        Stat stat1 = zk.setData("/ooxx", "newdata".getBytes(), 0);
        Stat stat2 = zk.setData("/ooxx", "newdata1".getBytes(), stat1.getVersion());

        System.out.println("-------------async start------------");
        zk.getData("/ooxx", false, new AsyncCallback.DataCallback() {
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println("---------async call back---------");
                System.out.println(o.toString());
                System.out.println(new String(bytes));
            }
        },"abc");

        System.out.println("------------async over-----------------");

        Thread.sleep(222222222);
    }
}
