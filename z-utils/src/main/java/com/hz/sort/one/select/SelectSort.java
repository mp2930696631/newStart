package com.hz.sort.one.select;

/**
 * @author zehua
 * @date 2021/3/7 11:16
 * <p>
 * 核心思想：每次选择最大的元素，然后再交换次数
 * 相比于冒泡排序，节省了很多无谓交换
 */
@SuppressWarnings("all")
public class SelectSort {

    public static void main(String[] args) {
        int[] array = new int[]{5, 9, 1, 6, 3, 2, 7};
        SelectSort.method01(array);
        SelectSort.print(array);
    }

    public static void method01(int[] array) {
        int length = array.length;

        for (int i = 0; i < length; i++) {
            int max = array[0];
            int maxIndex = 0;
            for (int j = 1; j < length - i; j++) {
                int temp = array[j];
                if (temp > max) {
                    max = temp;
                    maxIndex = j;
                }
            }

            array[maxIndex] = array[length - 1 - i];
            array[length - 1 - i] = max;
        }
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
