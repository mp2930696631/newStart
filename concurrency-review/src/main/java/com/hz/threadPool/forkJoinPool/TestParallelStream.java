package com.hz.threadPool.forkJoinPool;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zehua
 * @date 2020/11/28 19:52
 */
public class TestParallelStream {


    public static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        } else if (num == 2) {
            return true;
        } else {
            for (int i = 2; i < num; i++) {
                if (num / i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int count = 10000;
        int bound = 100000;
        Integer[] nums = new Integer[count];
        final ThreadLocalRandom localRandom = ThreadLocalRandom.current();
        for (int i = 0; i < count; i++) {
            nums[i] = localRandom.nextInt(bound + 1);
        }

        long start = System.currentTimeMillis();
        Arrays.stream(nums).forEach(TestParallelStream::isPrime);
        long end = System.currentTimeMillis();
        System.out.println("normal-----" + (end - start));

        start = System.currentTimeMillis();
        Arrays.stream(nums).parallel().forEach(TestParallelStream::isPrime);
        end = System.currentTimeMillis();
        System.out.println("normal-----" + (end - start));

        start = System.currentTimeMillis();
        Arrays.asList(nums).parallelStream().forEach(TestParallelStream::isPrime);
        end = System.currentTimeMillis();
        System.out.println("normal-----" + (end - start));
    }

}
