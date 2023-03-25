package com.yqj.zk.conf;

import org.apache.zookeeper.ZooKeeper;

public class ConfTest {
    public static void main(String[] args) throws InterruptedException {
        // 1.设置配置参数
        ZkUtils.setConnectString("localhost:2181/testConf");
        ZkUtils.setSessionTimeout(2000);
        ZkUtils.setDefaultWatcher(new DefaultWatcher());
        // 2.获取zk对象
        ZooKeeper zkClient = ZkUtils.getZk();
        // 3.从zk获取配置信息
        MyConf conf = new MyConf();
        String watchPath = "/appConf";
        WatchCallback watchCallback = new WatchCallback(zkClient, watchPath, conf);
        watchCallback.await();
        while (true) {
            if (conf.getConf() == null || "".equals(conf.getConf())) {
                System.out.println("conf lost...");
                watchCallback.await();
            }
            System.out.println(conf.getConf());
            Thread.sleep(2000);
        }
    }
}
