package com.hz.sort.two.merge;

/**
 * 归并排序
 *
 * @author zehua
 * @date 2021/3/7 18:33
 */
@SuppressWarnings("all")
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        MergeSort.method01(array);
        MergeSort.print(array);
    }

    public static void method01(int[] array) {
        recursion(array, 0, array.length - 1);
    }

    private static void recursion(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) >> 1;

        // 经过这一步，左右两部分已经有序
        recursion(array, start, mid);
        recursion(array, mid + 1, end);

        merge(array, start, mid, end);
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] tempArray = new int[end - start + 1];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tempIndex = 0;
        while (true) {
            if (array[leftIndex] <= array[rightIndex]) {
                tempArray[tempIndex++] = array[leftIndex];
                leftIndex++;
            } else {
                tempArray[tempIndex++] = array[rightIndex];
                rightIndex++;
            }

            if (leftIndex > mid || rightIndex > end) {
                break;
            }
        }

        while (leftIndex <= mid) {
            tempArray[tempIndex++] = array[leftIndex++];
        }

        while (rightIndex <= end) {
            tempArray[tempIndex++] = array[rightIndex++];
        }

        // 最后，改变原数组
        for (int i = start; i <= end; i++) {
            array[i] = tempArray[i - start];
        }
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
