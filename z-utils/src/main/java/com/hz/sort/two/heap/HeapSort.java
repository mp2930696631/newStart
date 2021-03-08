package com.hz.sort.two.heap;

/**
 * 堆排序
 *
 * @author zehua
 * @date 2021/3/7 19:10
 */
@SuppressWarnings("all")
public class HeapSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        HeapSort.method01(array);
        HeapSort.print(array);
    }

    // 大顶堆（需要使用大顶堆，而不是小顶堆，否则顺序是相反的）
    public static void method01(int[] array) {
        sort(array);
    }

    // 获取数组中最大的N个数
    // 需要使用小顶堆
    public static int[] getMaxN(int[] array, int n) {
        if (n == 1) {
            return getMax1(array);
        }

        if (n == 2) {
            return getMax2(array);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = array[i];
        }
        buildHeapMin(result);

        for (int i = n; i < array.length; i++) {
            if (array[i] > result[0]) {
                result[0] = array[i];
                sinkRecursionMin(result, 0, n);
            }
        }
        return result;
    }

    private static int[] getMax1(int[] array) {
        int[] result = new int[1];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        result[0] = max;

        return result;
    }

    private static int[] getMax2(int[] array) {
        int[] result = new int[2];
        int a = array[0];
        int b = array[1];
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        result[0] = a;
        result[1] = b;

        for (int i = 2; i < array.length; i++) {
            if (array[i] > result[1]) {
                result[0] = result[1];
                result[1] = array[i];
            } else if (array[i] > result[0]) {
                result[0] = array[i];
            }
        }

        return result;
    }

    private static void sort(int[] array) {
        buildHeapMax(array);
        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            sinkRecursion(array, 0, i);
        }
    }

    private static void buildHeapMax(int[] array) {
        int length = array.length;
        int startParent = (length - 1) >> 1;
        for (int i = startParent; i >= 0; i--) {
            // sinkRecursion(array, i, length);
            sink(array, i, length);
        }
    }

    private static void buildHeapMin(int[] array) {
        int length = array.length;
        int startParent = (length - 1) >> 1;
        for (int i = startParent; i >= 0; i--) {
            // sinkRecursion(array, i, length);
            sinkRecursionMin(array, i, length);
        }
    }

    private static void sinkRecursion(int[] array, int parent, int border) {
        int temp = array[parent];
        int child = (parent << 1) + 1;
        if (child >= border) {
            return;
        }

        // 也就是存在右孩子
        if (child + 1 < border) {
            // 也就是找到两个孩子中值最大的孩子
            if (array[child + 1] > array[child]) {
                child = child + 1;
            }
        }

        if (array[child] <= temp) {
            return;
        }

        array[parent] = array[child];
        array[child] = temp;
        sinkRecursion(array, child, border);

    }

    // 下沉（非递归实现）
    private static void sink(int[] array, int parent, int border) {
        int temp = array[parent];
        int child = (parent << 1) + 1;
        while (child < border) {
            // 也就是存在右孩子
            if (child + 1 < border) {
                // 也就是找到两个孩子中值最大的孩子
                if (array[child + 1] > array[child]) {
                    child = child + 1;
                }
            }

            if (array[child] <= temp) {
                break;
            }

            array[parent] = array[child];
            parent = child;
            child = (child << 1) + 1;
        }
        array[parent] = temp;

    }

    // 下沉的递归实现（小顶堆）
    private static void sinkRecursionMin(int[] array, int parent, int border) {
        int temp = array[parent];
        int child = (parent << 1) + 1;
        if (child >= border) {
            return;
        }

        if (child + 1 < border) {
            if (array[child + 1] < array[child]) {
                child++;
            }
        }

        if (array[child] >= temp) {
            return;
        }

        array[parent] = array[child];
        array[child] = temp;
        sinkRecursionMin(array, child, border);
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
