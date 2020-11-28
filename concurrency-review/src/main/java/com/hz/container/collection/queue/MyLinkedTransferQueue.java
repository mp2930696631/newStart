package com.hz.container.collection.queue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zehua
 * @date 2020/11/28 10:10
 */
public class MyLinkedTransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();
        /*linkedTransferQueue.put("ooo");
        linkedTransferQueue.put("ooofrefg");*/
        new Thread(()->{
            try {
                System.out.println(linkedTransferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        linkedTransferQueue.transfer("ooode");
        //System.out.println(linkedTransferQueue.take());

        //System.out.println("ooo ");
    }

}
