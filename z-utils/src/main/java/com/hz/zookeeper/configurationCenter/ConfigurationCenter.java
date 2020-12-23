package com.hz.zookeeper.configurationCenter;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 *  * 重点，Conf变量加volatile或者其data属性加volatile，因为使用它的线程和设置它的线程并不是一个，
 *  * 这是在测试中很难发现的bug，因为我们在测试中一般会使用Thread.sleep或者System.out.println,他们俩也可以保证可见性，产生干扰
 *  * 所以，在找可见性bug的时候，只能使用死循环
 *  * 目前这个Mian程序是可以发现可见性bug的，可以试一下加或者不加volatile的情况，有何不同
 *
 * @author zehua
 * @date 2020/12/23 16:37
 */
public class ConfigurationCenter {
    private static final String ADDRESS = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/configurationCenter";
    private Conf conf = new Conf();
    private ZooKeeper zk;
    private WatcherAndCallback wacb;


    public ConfigurationCenter() {
        this.zk = ZkUtils.getZk(ADDRESS, 2000);
        this.wacb = new WatcherAndCallback();
    }

    public Conf getConf() {
        zk.exists("/", wacb, wacb, "hello");
        wacb.sync();
        return conf;
    }

    private class WatcherAndCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
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
        public void process(WatchedEvent event) {
            final Event.EventType type = event.getType();
            switch (type) {
                case NodeCreated:
                case NodeDataChanged:
                    zk.getData("/", this, this, "hello");
                    break;
                case NodeDeleted:
                    conf.setData(null);
                    // System.out.println("node deleted.....");
                    zk.exists("/", this, this, "hello");
                    break;
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (stat != null) {
                zk.getData("/", null, this, "hello");
            } else {
                System.out.println("node does not exist.....wait to create....");
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            try {
                conf.setData(new String(data));
                countDownLatch.countDown();
            } catch (Exception e) {
                System.out.println("node data is null....wait.....");
            }
        }
    }

}
