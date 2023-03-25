package com.yqj.zk.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LockCallback implements Watcher,AsyncCallback.StringCallback, AsyncCallback.Children2Callback {
    private final ZooKeeper zkClient;
    private final String threadName;
    private String lockName;
    private final CountDownLatch cc = new CountDownLatch(1);

    public LockCallback(ZooKeeper zkClient, String threadName) {
        this.zkClient = zkClient;
        this.threadName = threadName;
    }

    // 上锁
    public void lock() {
        try {
            // 创建临时序列化节点，用于串行获取分布式锁
            zkClient.create("/lock",threadName.getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,this,"create");
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 释放锁
    public void unLock() {
        try {
            zkClient.delete("/"+lockName,-1);
            System.out.println(threadName + " unlock success, lockName: " + lockName);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    // create方法回调
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        if (name != null) {
            lockName = name.substring(1); // 获取创建的临时节点的名字
            zkClient.getChildren("/",false,this,"getChildren"); // 获取当前路径下的子节点
        }
    }

    // getChildren方法回调
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        if (children != null && children.size() != 0) {
            // 节点排序
            Collections.sort(children);
            // 获取当前临时节点的索引
            int i = children.indexOf(lockName);
            // 若当前节点为头节点则加锁成功
            if (i < 1) {
                System.out.println(threadName + " get lock, lockName: " + lockName);
                cc.countDown();
            } else {
                // 加锁失败，监听前一个节点，等待前一个节点释放锁
                try {
                    zkClient.exists("/" + children.get(i - 1),this);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 监听器
    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        // 监听到节点删除事件
        if (type == Event.EventType.NodeDeleted) {
            zkClient.getChildren("/",false,this,"getChildren");
        }
    }
}
