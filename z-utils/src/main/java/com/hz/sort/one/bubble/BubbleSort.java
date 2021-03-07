package com.hz.sort.one.bubble;

/**
 * @author zehua
 * @date 2021/3/7 8:38
 * <p>
 * 核心思想：只与相邻的两个元素有关
 */
@SuppressWarnings("all")
public class BubbleSort {

    public static void main(String[] args) {
        // int[] array = new int[]{5, 9, 1, 6, 3, 2, 7};
        // BubbleSort.method01(array);
        // BubbleSort.method02(array);
        // BubbleSort.method03(array);
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        BubbleSort.cockTailSort(array);
        BubbleSort.print(array);
    }

    /**
     * 最基础的实现
     *
     * @param array
     */
    public static void method01(int[] array) {
        int length = array.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int a = array[j];
                int b = array[j + 1];
                if (a > b) {
                    array[j] = b;
                    array[j + 1] = a;
                }
            }
        }
    }

    /**
     * 优化一：如果没有交换，说明已经有序,使用一个标志位
     *
     * @param array
     */
    public static void method02(int[] array) {
        int length = array.length;

        for (int i = 0; i < length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < length - i - 1; j++) {
                int a = array[j];
                int b = array[j + 1];
                if (a > b) {
                    array[j] = b;
                    array[j + 1] = a;
                    isSorted = false;
                }
            }

            if (isSorted) {
                return;
            }
        }
    }

    /**
     * 优化二：记录最后一次交换的位置
     *
     * @param array
     */
    public static void method03(int[] array) {
        int length = array.length;

        int lastExchangeIndex = length - 1;
        for (int i = 0; i < length; i++) {
            boolean isSorted = true;
            int tempIndex = lastExchangeIndex;
            for (int j = 0; j < tempIndex; j++) {
                int a = array[j];
                int b = array[j + 1];
                if (a > b) {
                    array[j] = b;
                    array[j + 1] = a;
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }

            if (isSorted) {
                return;
            }
        }
    }

    /**
     * 优化三：鸡尾酒排序
     *
     * @param array
     */
    public static void cockTailSort(int[] array) {
        int length = array.length;

        int leftExchangeIndex = 0;
        int rightExchangeIndex = length - 1;
        for (int i = 0; i < length; i++) {
            boolean isSorted = true;
            int tempLeft = leftExchangeIndex;
            int tempRigth = rightExchangeIndex;
            if ((i & 1) == 0) {
                for (int j = tempLeft; j < tempRigth; j++) {
                    int a = array[j];
                    int b = array[j + 1];
                    if (a > b) {
                        array[j] = b;
                        array[j + 1] = a;
                        isSorted = false;
                        rightExchangeIndex = j;
                    }
                }
            } else {
                for (int j = tempRigth; j > tempLeft; j--) {
                    int a = array[j];
                    int b = array[j - 1];
                    if (a < b) {
                        array[j] = b;
                        array[j - 1] = a;
                        isSorted = false;
                        leftExchangeIndex = j - 1;
                    }
                }
            }

            if (isSorted) {
                return;
            }
        }

    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
