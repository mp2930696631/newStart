package com.zehua.review.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 测试：SynchronousQueue是否带缓存
 * 结果，不带缓存，见下面代码
 * @Auther zehua
 * @Date 2020/10/30 15:38
 */
public class TestBlockingQueue {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();
        // BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 注意：这句话并不会执行，说明SynchronousQueue并不能缓存，添加后必须马上拿走才可以
        System.out.println("123");
    }

}
