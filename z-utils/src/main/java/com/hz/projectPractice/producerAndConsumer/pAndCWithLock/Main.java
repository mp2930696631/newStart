package com.hz.projectPractice.producerAndConsumer.pAndCWithLock;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zehua
 * @date 2020/12/23 11:27
 */
public class Main {
    private static Queue<String> queue = new ArrayDeque<>();
    private static int size;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition putCond = lock.newCondition();
    private static Condition takeCond = lock.newCondition();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new ProducerWithLock(queue, 5, lock, takeCond, putCond, i+1), "producer " + (i + 1)).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(new ConsumerWithLock(queue, 5, lock, takeCond, putCond), "consumer " + (i + 1)).start();
        }
    }

}
