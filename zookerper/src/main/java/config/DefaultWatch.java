package config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class DefaultWatch  implements Watcher {

    CountDownLatch cd ;

    public  void  setCd (CountDownLatch Cd){
        this.cd = Cd;
    }

    public void process(WatchedEvent watchedEvent) {

        System.out.println(watchedEvent.toString());
        switch (watchedEvent.getState()){

            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                cd.countDown();
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
    }
}
