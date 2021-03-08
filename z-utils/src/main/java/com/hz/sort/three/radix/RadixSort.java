package com.hz.sort.three.radix;

import java.util.ArrayDeque;

/**
 * 基数排序：也就是对字符串按位进行计数排序
 * (需要使用稳定的计数排序才行)
 *
 * @author zehua
 * @date 2021/3/8 10:18
 */
@SuppressWarnings("all")
public class RadixSort {

    public static void main(String[] args) {
        /*System.out.println((int) 'a');
        System.out.println((int) 'A');
        System.out.println((int) '0');*/

        /*String[] array = new String[]{"abc", "acc", "fdf", "ter", "uyh", "uii", "dec", "opr"};
        String[] array02 = new String[]{"abc", "acc", "fdf", "ter", "uyh", "uii", "dec", "opr"};*/
        String[] array = new String[]{"abc", "acc", "acc", "der", "ceh", "ai", "decf", "dec"};
        String[] array02 = new String[]{"abc", "acc", "acc", "der", "ceh", "ai", "decf", "dec"};
        MSD(array);
        LSD(array02);
        print(array);
        print(array02);
    }

    // 从高位开始进行排序
    public static void MSD(String[] array) {
        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() > maxLength) {
                maxLength = array[i].length();
            }
        }
        MSD_recursion(array, 0, array.length - 1, 0, maxLength - 1);
    }

    // 从低位开始进行排序（这个相对于从高位开始排序的算法较简单，因为只需要从各位开始，一直排到最高位，
    // 不需要考虑其他情况，只需要使用稳定的计数排序，最后的结果就是正确的）
    public static void LSD(String[] array) {
        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].length() > maxLength) {
                maxLength = array[i].length();
            }
        }

        for (int x = maxLength - 1; x >= 0; x--) {
            final char[] maxMin = getMaxMin(array, 0, array.length - 1, x);
            int min = maxMin[0];
            int max = maxMin[1];
            int length = max - min + 1;
            int[] nums = new int[length];
            for (int i = 0; i <= array.length - 1; i++) {
                int c = getChar(array[i], x);

                int index = c - min;
                nums[index]++;
            }
            // 为了稳定性！！,需要进行特殊处理
            for (int i = 1; i < length; i++) {
                nums[i] = nums[i - 1] + nums[i];
            }

            String[] temp = new String[array.length];
            for (int i = array.length - 1; i >= 0; i--) {
                int c = getChar(array[i], x);

                int index = c - min;
                temp[--nums[index]] = array[i];
            }

            for (int i = 0; i <= array.length - 1; i++) {
                array[i] = temp[i];
            }
        }
    }

    private static void MSD_recursion(String[] array, int start, int end, int indexAt, int maxIndex) {
        if (indexAt > maxIndex) {
            return;
        }

        final ArrayDeque<Integer> stack = countingSort(array, start, end, indexAt);
        if (stack == null) {
            return;
        }
        // 递归
        while (!stack.isEmpty()) {
            int a = stack.pollFirst();
            int b = stack.pollFirst();
            MSD_recursion(array, b, a, indexAt + 1, maxIndex);
        }
    }

    /**
     * 可以将array在[start, end]下标之间进行计数排序
     * 并且，返回需要进行递归的ArrayDeque
     *
     * @param array
     * @param start
     * @param end
     * @param indexAt
     * @param maxMin
     * @return
     */
    public static ArrayDeque<Integer> countingSort(String[] array, int start, int end, int indexAt) {
        final char[] maxMin = getMaxMin(array, start, end, indexAt);
        if (maxMin == null) {
            return null;
        }

        int min = maxMin[0];
        int max = maxMin[1];
        int length = max - min + 1;
        int[] nums = new int[length];
        for (int i = start; i <= end; i++) {
            int c = getChar(array[i], indexAt);

            int index = c - min;
            nums[index]++;
        }
        // 为了稳定性！！,需要进行特殊处理
        for (int i = 1; i < length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        // 只有本元素重复了，才进行递归，比较下一个元素,将start、end放入栈中
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        if (nums[0] > 1) {
            stack.addFirst(0 + start);
            stack.addFirst(nums[0] - 1 + start);
        }
        for (int i = 1; i < length; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                stack.addFirst(nums[i - 1] + start);
                stack.addFirst(nums[i] - 1 + start);
            }
        }

        String[] temp = new String[end - start + 1];
        for (int i = end; i >= start; i--) {
            int c = getChar(array[i], indexAt);

            int index = c - min;
            temp[--nums[index]] = array[i];
        }

        for (int i = start; i <= end; i++) {
            array[i] = temp[i - start];
        }

        return stack;
    }

    // 对于不等长的字符串，末尾补零（关键方法）
    private static char getChar(String str, int indexAt) {
        if (str.length() <= indexAt) {
            return '0';
        }

        return str.charAt(indexAt);
    }

    // 获取某段内的最大和最小的字符
    private static char[] getMaxMin(String[] array, int start, int end, int indexAt) {
        int length = array.length;

        char min = 'z';
        char max = '0';
        for (int i = start; i <= end; i++) {
            char c = getChar(array[i], indexAt);

            if (c < min) {
                min = c;
            }

            if (c > max) {
                max = c;
            }
        }

        if (max == '0') {
            return null;
        }

        return new char[]{min, max};
    }

    public static void print(String[] array) {
        for (String i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
