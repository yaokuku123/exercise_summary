package com.yqj.zk.conf;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class DefaultWatcher implements Watcher {

    private CountDownLatch cc;

    public CountDownLatch getCc() {
        return cc;
    }

    public void setCc(CountDownLatch cc) {
        this.cc = cc;
    }

    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        switch (state) {
            case SyncConnected:
                System.out.println("zk connected...");
                cc.countDown(); // zk连接建立完成
                break;
            case Disconnected:
                System.out.println("zk disconnected...");
                break;
        }
    }
}
