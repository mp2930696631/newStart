package com.hz.sort.two.heap;

/**
 * @author zehua
 * @date 2021/3/7 20:40
 */
public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        final int[] maxN = HeapSort.getMaxN(array, 2);
        HeapSort.print(maxN);
    }

}
