package com.hz.zookeeper.distributedLock;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 19:57
 */
public class Main {
    private static final String address = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/distributedLock";
    private static ZooKeeper zk = ZkUtils.getZk(address, 2000);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 每new一个lock就相当于新创建一个zookeeper客户端实例
                DistributedLock lock = new DistributedLock(zk);
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " doing.............");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }).start();
        }
    }

}
