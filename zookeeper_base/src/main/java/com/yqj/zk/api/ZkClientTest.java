package com.yqj.zk.api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkClientTest {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        // 1.创建Zk客户端
        // 第一类：new zk 时候，传入的watch，这个watch，session级别的，跟path 、node没有关系。
        CountDownLatch cc = new CountDownLatch(1);
        ZooKeeper zkClient = new ZooKeeper("localhost:2181", 2000, new Watcher() {
            // Watch 的回调方法
            @Override
            public void process(WatchedEvent event) {
                Event.KeeperState state = event.getState();
                if (state == Event.KeeperState.SyncConnected) { // 完成连接建立
                    System.out.println("zookeeper connected");
                    cc.countDown();
                }
            }
        });
        // 阻塞等待zk建立完成
        cc.await();
        System.out.println("isConnected：" + zkClient.getState().isConnected()); // true

        // 2.查看节点是否存在，若存在则删除
        String path = "/test";
        if (zkClient.exists(path, false) != null) {
            deleteAll(zkClient, path);
        }

        // 3.创建节点（阻塞式创建）
        String pathName = zkClient.create(path, "hello world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(pathName); // /test

        // 4.获取节点数据，并添加监听器
        Stat stat = new Stat();
        byte[] data = zkClient.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("getData watch: " + event);
                try {
                    // 重新注册监听器
                    zkClient.getData(path, this, null);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, stat);
        System.out.println(new String(data)); // hello world
        System.out.println(stat); // 206,206,1679625730963,1679625730963,0,0,0,0,11,0,206

        // 4.设置数据，并导致触发回调
        zkClient.setData(path, "hello zookeeper".getBytes(), -1);
        Thread.sleep(10); // 等待监听器完成重新注册，否则有概率在未注册完成监听器时已经完成了新数据的设置，导致未监听到本次修改的事件
        zkClient.setData(path, "hello zookeeper again".getBytes(), -1);

        // 5.再次获取数据
        data = zkClient.getData(path, false, stat);
        System.out.println(new String(data)); // hello zookeeper again
        System.out.println(stat); // 206,208,1679625730963,1679625730985,2,0,0,0,21,0,206

        // 关闭资源
        zkClient.close();
    }

    // 递归删除当前节点及其所有子节点
    public static void deleteAll(ZooKeeper zkClient, String path) throws KeeperException, InterruptedException {
        // 获取节点的所有子节点
        List<String> children = zkClient.getChildren(path, false); // 得到的子节点不带前缀"/"
        // 遍历子节点递归删除
        for (String child : children) {
            deleteAll(zkClient, path + "/" + child);
        }
        // 删除节点
        zkClient.delete(path, -1);
    }
}
