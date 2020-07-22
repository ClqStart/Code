package lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;


import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WatchCallBack implements Watcher, AsyncCallback.StringCallback, AsyncCallback.Children2Callback, AsyncCallback.StatCallback {

    ZooKeeper zk;
    String treadName;
    CountDownLatch cc = new CountDownLatch(1);
    String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getTreadName() {
        return treadName;
    }

    public void setTreadName(String treadName) {
        this.treadName = treadName;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void tryLock() {
      try{
        zk.create("/lock", treadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, "abc");
        cc.await();
      }catch (InterruptedException e){
          e.printStackTrace();
      }
    }

    public void unlock() {
        try {
            zk.delete(pathName,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }
    public void process(WatchedEvent event) {
        switch (event.getType()){
            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                zk.getChildren("/",false,this,"abc");
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
        }
    }


    public void processResult(int i, String path, Object o, String name) {
        if(name !=null){
            System.out.println(name);
            pathName = name;
            zk.getChildren("/",false,this,"dfe");
        }
    }

    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        //一定会看到前面的节点,基本都可以看到，list里面是乱序的

        Collections.sort(children);
        //1、是不是第一个
        int i = children.indexOf(pathName.substring(1));
        //2、
        if(i==0){
            System.out.println(treadName+" i am first");
            cc.countDown();
        }else{
            zk.exists("/"+children.get(i-1), this,this,"dfr");
        }
    }

    public void processResult(int i, String s, Object o, Stat stat) {

    }


}
