package com.hz.sort.three.counting;

/**
 * 计数排序
 *
 * 局限性：只适用于跨度不大的整数（或者是可以转化为整数的字符）
 *
 * @author zehua
 * @date 2021/3/8 8:37
 */
@SuppressWarnings("all")
public class CountingSort {

    public static void main(String[] args) {
        int[] array = new int[]{8,8, 4, 5, 4, 4, 9, 6, 1, 1, 2, 9, 9, 9};
        method01(array);
        print(array);
    }

    // 稳定的计数排序(如果是从原数组左侧开始遍历的话（doSort01），使用这种方式，也就是nums的下标从1开始)
    public static void method01(int[] array) {
        final int[] maxMin = getMaxMin(array);
        int min = maxMin[0];
        int max = maxMin[1];
        // 下标从1开始,
        int length = max - min + 1 + 1;
        int[] nums = new int[length];
        for (int i = 0; i < array.length; i++) {
            int index = array[i] - min + 1;
            nums[index]++;
        }

        // 为了稳定性！！,需要进行特殊处理
        for (int i = 1; i < length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }

        final int[] ints = doSort01(array, nums, min);
        for (int i = 0; i < array.length; i++) {
            array[i] = ints[i];
        }
    }

    // 稳定的计数排序(如果是从原数组右侧开始遍历的话(doSort02)，使用这种方式，也就是nums的下标从0开始)
    public static void method02(int[] array) {
        final int[] maxMin = getMaxMin(array);
        int min = maxMin[0];
        int max = maxMin[1];
        // 下标从0开始,
        int length = max - min + 1;
        int[] nums = new int[length];
        for (int i = 0; i < array.length; i++) {
            int index = array[i] - min;
            nums[index]++;
        }

        // 为了稳定性！！,需要进行特殊处理
        for (int i = 1; i < length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }

        final int[] ints = doSort02(array, nums, min);
        for (int i = 0; i < array.length; i++) {
            array[i] = ints[i];
        }
    }

    private static int[] doSort01(int[] array, int[] nums, int min) {
        int length = array.length;
        int[] result = new int[length];

        // 从原数组左侧开始遍历的话
        for (int i = 0; i < length; i++) {
            int index = array[i] - min;
            result[nums[index]++] = array[i];
        }

        return result;
    }

    private static int[] doSort02(int[] array, int[] nums, int min) {
        int length = array.length;
        int[] result = new int[length];

        for (int i = length - 1; i >= 0; i--) {
            int index = array[i] - min;
            result[--nums[index]] = array[i];
        }

        return result;
    }

    private static int[] getMaxMin(int[] array) {
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }

            if (array[i] < min) {
                min = array[i];
            }
        }

        int[] result = new int[]{min, max};

        return result;
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
