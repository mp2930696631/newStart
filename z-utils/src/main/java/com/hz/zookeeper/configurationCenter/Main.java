package com.hz.zookeeper.configurationCenter;

/**
 * * 重点，Conf变量加volatile或者其data属性加volatile，因为使用它的线程和设置它的线程并不是一个，
 * * 这是在测试中很难发现的bug，因为我们在测试中一般会使用Thread.sleep或者System.out.println,他们俩也可以保证可见性，产生干扰
 * * 所以，在找可见性bug的时候，只能使用死循环
 * * 目前这个Mian程序是可以发现可见性bug的，可以试一下加或者不加volatile的情况，有何不同
 *
 * @author zehua
 * @date 2020/12/23 16:50
 */
public class Main {

    public static void main(String[] args) {
        // 可见性bug版本
        ConfigurationCenter cc = new ConfigurationCenter();
        Conf conf = cc.getConf();
        while (true) {
            if (conf.getData() == null) {
                break;
                // conf = cc.getConf();
            }

            // System.out.println(conf.getData());
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

        // 测试版本
        /*ConfigurationCenter cc = new ConfigurationCenter();
        Conf conf = cc.getConf();
        while (true){
            if (conf.getData()==null){
                conf = cc.getConf();
            }

            System.out.println(conf.getData());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}
