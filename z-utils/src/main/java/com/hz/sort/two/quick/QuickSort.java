package com.hz.sort.two.quick;

import java.util.ArrayDeque;

/**
 * @author zehua
 * @date 2021/3/7 14:42
 */
@SuppressWarnings("all")
public class QuickSort {

    public static void main(String[] args) {
        int[] array = new int[]{5, 9, 1, 6, 3, 2, 7};
        int[] array01 = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        // QuickSort.method01(array);
        // QuickSort.method02(array01);
        QuickSort.method03(array01);
        QuickSort.print(array01);
    }

    // 指针交换法（注意：需要从右指针开始（见partition01Right方法）！！，
    // 如果从左指针开始的话（见partition01Left方法），需要加一些额外的东西！！）
    // 相比于挖坑法，减少了交换次数
    public static void method01(int[] array) {
        recursion01(array, 0, array.length - 1);
    }

    // 挖坑法(注意：必须从右指针开始)
    public static void method02(int[] array) {
        recursion02(array, 0, array.length - 1);
    }

    // 非递归实现（很简单其实：就是使用栈来存储start和end，然后每次取都是两个两个数的取）
    public static void method03(int[] array) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(0);
        stack.addFirst(array.length - 1);
        while (!stack.isEmpty()) {
            int end = stack.pollFirst();
            int start = stack.pollFirst();
            int index = partition01Right(array, start, end);
            if (start < index - 1) {
                stack.addFirst(start);
                stack.addFirst(index - 1);
            }

            if (index + 1 < end) {
                stack.addFirst(index + 1);
                stack.addFirst(end);
            }
        }
    }

    private static void recursion01(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int index = partition01Right(array, start, end);
        // 递归
        recursion01(array, start, index - 1);
        recursion01(array, index + 1, end);
    }

    private static void recursion02(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }

        int index = partition02(array, start, end);
        // 递归
        recursion02(array, start, index - 1);
        recursion02(array, index + 1, end);
    }

    // 指针交换法(从右指针开始)
    private static int partition01Right(int[] array, int start, int end) {
        int num = array[start];

        int left = start;
        int right = end;

        while (left != right) {
            while (left < right) {
                if (array[right] < num) {
                    break;
                }
                right--;
            }

            while (left < right) {
                if (array[left] > num) {
                    break;
                }
                left++;
            }

            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        array[start] = array[left];
        array[left] = num;

        return left;
    }

    // 指针交换法(从左指针开始,需要使用left+1来进行判断)
    private static int partition01Left(int[] array, int start, int end) {
        int num = array[start];

        int left = start;
        int right = end;

        while (left != right) {
            while (left < right) {
                if (array[left + 1] > num) {
                    break;
                }
                left++;
            }

            while (left < right) {
                if (array[right] < num) {
                    break;
                }
                right--;
            }

            if (left < right) {
                int temp = array[left + 1];
                array[left + 1] = array[right];
                array[right] = temp;
                left++;
            }
        }
        array[start] = array[left];
        array[left] = num;

        return left;
    }

    // 挖坑法
    private static int partition02(int[] array, int start, int end) {
        int num = array[start];
        int hole = start;

        int left = start;
        int right = end;

        while (left != right) {
            while (left < right) {
                if (array[right] < num) {
                    array[hole] = array[right];
                    hole = right;
                    break;
                }
                right--;
            }

            while (left < right) {
                if (array[left] > num) {
                    array[hole] = array[left];
                    hole = left;
                    break;
                }
                left++;
            }
        }

        array[hole] = num;

        return hole;
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
