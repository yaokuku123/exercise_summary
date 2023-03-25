package com.yqj.zk.lock;

import com.yqj.zk.conf.DefaultWatcher;
import com.yqj.zk.conf.ZkUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        // 1.创建Zk对象
        ZkUtils.setConnectString("localhost:2181/testLock");
        ZkUtils.setSessionTimeout(2000);
        ZkUtils.setDefaultWatcher(new DefaultWatcher());
        ZooKeeper zkClient = ZkUtils.getZk();
        // 2.创建10个线程模拟访问临界资源，分布式锁实现对资源的串行访问
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 3.创建分布式锁
                String threadName =  Thread.currentThread().getName();
                LockCallback lockCallback = new LockCallback(zkClient,threadName);
                // 4.上锁
                lockCallback.lock();
                // 5.访问临界资源
                try {
                    byte[] data = zkClient.getData("/", false, null);
                    System.out.println(threadName + " at work, get data: " + new String(data));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
                // 6.释放锁
                lockCallback.unLock();
            }).start();
        }
        Thread.sleep(Long.MAX_VALUE);
    }
}
