package com.yqj.zk.conf;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class WatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
    private ZooKeeper zkClient;
    private String watchPath;
    private MyConf conf;
    private CountDownLatch cc = new CountDownLatch(1);

    public WatchCallback() {
    }

    // 设置zk对象，监听的节点，获取的配置文件存储的对象
    public WatchCallback(ZooKeeper zkClient, String watchPath, MyConf conf) {
        this.zkClient = zkClient;
        this.watchPath = watchPath;
        this.conf = conf;
    }

    // 尝试获取配置文件，若无法获取则阻塞
    public void await() {
        try {
            zkClient.exists(watchPath, this, this, "exists");
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 监听器
    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        switch (type) {
            // 节点创建事件
            case NodeCreated:
                // 触发创建事件后，尝试获取节点的数据
                zkClient.getData(watchPath,this,this,"getData");
                break;
            // 节点删除事件
            case NodeDeleted:
                // 触发删除事件后，将配置文件内容置空并设置cc阻塞访问
                conf.setConf("");
                cc = new CountDownLatch(1);
                break;
            // 节点变更事件
            case NodeDataChanged:
                // 触发变更事件，尝试重新获取节点的数据
                zkClient.getData(watchPath,this,this,"getData");
                break;
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        if (stat != null) {
            zkClient.getData(watchPath,this,this,"getData");
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        if (data != null) {
            // 设置最新的配置文件数据
            conf.setConf(new String(data));
            // 解除阻塞
            cc.countDown();
        }
    }
}
