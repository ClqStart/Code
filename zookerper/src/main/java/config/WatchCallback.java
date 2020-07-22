package config;



import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {

   CountDownLatch cl = new CountDownLatch(1);

    private ZooKeeper zk;

    private MyConf myConf;

    public MyConf getMyConf() {
        return myConf;
    }

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }


    /**
     * watch回调方法
     * @param event
     */

    public void process(WatchedEvent event) {

        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zk.getData("/AppConf",this,this,"aaa");//这里用 更合适一些
//                zk.exists("/AppConf",this, this,"aaa");
                break;
            case NodeDeleted:
                //如果有人删除了节点，让服务阻塞
                myConf.setConf("");
                cl=new CountDownLatch(1);
                break;
            case NodeDataChanged:
                /**
                 * 如果数据被修改，则再次回调从新设置配置文件并且监控
                 */
                zk.getData("/AppConf",this,this,"aaa");
                break;
            case NodeChildrenChanged:
                break;
        }

    }
    public  void aWait(){
        zk.exists("/AppConf",this,this,"abc");
        try {
            cl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * exists回调方法
     * @param rc
     * @param path
     * @param ctx
     * @param stat
     */

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        //confTest节点存在
        if (stat!=null){
            //获取数据
            zk.getData("/AppConf",this,this,"aaa");
        }
    }

    /**
     * getData 的回调
     * @param rc
     * @param path
     * @param ctx
     * @param data
     * @param stat
     */

    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {

        if (data!=null){
            //拿回配置文件，封装入Entry
            myConf.setConf(new String(data));
            //ConfTest类中的阻塞
            cl.countDown();
        }

    }
}