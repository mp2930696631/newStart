package com.hz.zookeeper.baseCRUD;

import com.hz.encryption.EnDecrypt;
import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zehua
 * @date 2020/12/22 11:47
 */
public class CRUDtest {
    private static final String address = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/zehua";

    public static void main(String[] args) {

        final ZooKeeper zk = CRUDutils.getZk(address, 2000);
        String authStr = "user1:123456";
        CRUDutils.setAuthInfo(zk, "digest", authStr);

        // C
        System.out.println("------------------C-------------------");
        final ZkUtils.NodeObj nodeObj = new ZkUtils.NodeObjBuilder().build();
        CRUDutils.create(zk, "/node02"
                , "hello zookeeper node02".getBytes()
                , ZooDefs.Ids.OPEN_ACL_UNSAFE
                , CreateMode.EPHEMERAL, nodeObj, "hello zehua");

        CRUDutils.sync();
        System.out.println("------------------C-------------------");

        // A
        System.out.println("-----------------A------------------");
        String str = "user1:123456";
        final String s = EnDecrypt.base64ThenSha1(str);
        String idStr = "user1:" + s;
        Id id = new Id("digest", idStr);
        ACL acl = new ACL(0b11111, id);
        List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        CRUDutils.setAcl(zk, "/node02", acls, 0, nodeObj, "hello zehua");
        CRUDutils.sync();
        System.out.println("-----------------A------------------");

        // R
        System.out.println("-----------------R-----------------");
        CRUDutils.get(zk, "/node02", null, nodeObj, "hello zehua");
        CRUDutils.sync();
        System.out.println("-----------------R-----------------");

        // w
        System.out.println("---------------W------------------");
        CRUDutils.set(zk, "/node02"
                , "new data".getBytes()
                , 3, nodeObj, "hello zehua");

        CRUDutils.sync();
        System.out.println("---------------W------------------");

        // d
        System.out.println("------------------D-----------------------");
        CRUDutils.delete(zk, "/node02", nodeObj.getStat().getVersion(), nodeObj, "hello zehua");
        CRUDutils.sync();
        System.out.println("-------------------------D---------------------");
    }

}
