package com.hz.zookeeper.zookeeperAtomic;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/23 12:45
 */
public class ZookeeperAtomic {
    private static final String ADDRESS = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/atomicIncrement";
    private ZooKeeper zk;
    private MyCallBack callBack;
    private ZkUtils.NodeObj nodeObj;
    private boolean flag = true;

    public ZookeeperAtomic() {
        this.zk = ZkUtils.getZk(ADDRESS, 2000);
        this.callBack = new MyCallBack();
        this.nodeObj = new ZkUtils.NodeObj();
    }

    // 先获取，在自增
    public int getAndIncrement() {
        int update = 0;
        while (true) {
            zk.getData("/", null, callBack, "hello");
            callBack.sync();
            final String data = nodeObj.getData();
            final Stat stat = nodeObj.getStat();
            update = Integer.valueOf(data) + 1;
            zk.setData("/", (update + "").getBytes(), stat.getVersion(), callBack, "hello");
            callBack.sync();
            if (flag) {
                break;
            }
        }

        return update - 1;
    }

    // 先自增，再获取
    public int incrementAndGet() {
        int update = 0;
        while (true) {
            zk.getData("/", null, callBack, "hello");
            callBack.sync();
            final String data = nodeObj.getData();
            final Stat stat = nodeObj.getStat();
            update = Integer.valueOf(data) + 1;
            zk.setData("/", (update + "").getBytes(), stat.getVersion(), callBack, "hello");
            callBack.sync();
            if (flag) {
                break;
            }
        }

        return update;
    }

    private class MyCallBack implements AsyncCallback.DataCallback, AsyncCallback.StatCallback {
        private CountDownLatch countDownLatch = new CountDownLatch(1);

        public void sync() {
            try {
                countDownLatch.await();
                countDownLatch = new CountDownLatch(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            try {
                nodeObj.setData(new String(data));
                nodeObj.setStat(stat);
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (stat == null) {
                // 更新失败
                // 什么也不做，继续尝试
                flag = false;
                countDownLatch.countDown();
            } else {
                flag = true;
                countDownLatch.countDown();
            }
        }
    }

}
