package com.hz.zookeeper.configurationCenter;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 12:48
 */
public class ConfigurationCenter {

    private ZkUtils.MyWatcher myWatcher;
    private ZkUtils.MyCallBack myCallBack;
    private ZooKeeper zk;
    private ZkUtils.NodeObj nodeObj;

    public ConfigurationCenter(String address, int timeout) {
        this.zk = ZkUtils.getZk(address, 2000);
        this.nodeObj = new ZkUtils.NodeObjBuilder().build();
        this.myCallBack = new ZkUtils.MyCallBack(nodeObj, zk);
        this.myWatcher = new ZkUtils.MyWatcher(nodeObj, zk, this.myCallBack);
    }

    public ZkUtils.NodeObj getConf(String nodePath) {
        zk.exists(nodePath, myWatcher, myCallBack, "hello");
        myCallBack.sync();

        return nodeObj;
    }

}
