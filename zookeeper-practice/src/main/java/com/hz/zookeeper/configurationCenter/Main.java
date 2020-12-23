package com.hz.zookeeper.configurationCenter;

import com.hz.zookeeper.ZkUtils;

/**
 * @author zehua
 * @date 2020/12/22 16:07
 */
public class Main {
    private static final String address = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/zehua";

    public static void main(String[] args) {
        ConfigurationCenter configurationCenter = new ConfigurationCenter(address, 2000);
        final ZkUtils.NodeObj conf = configurationCenter.getConf("/node01");

        while (true){
            if (conf.getData()==null){
                configurationCenter.getConf("/node01");
            }else {
                System.out.println("data: "+conf.getData());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
