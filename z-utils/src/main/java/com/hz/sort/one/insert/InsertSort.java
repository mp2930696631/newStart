package com.hz.sort.one.insert;

/**
 * @author zehua
 * @date 2021/3/7 11:32
 * <p>
 * 核心思想：从数组的左边开始遍历，数组左边的元素是有序的，然后将右边的元素一个一个的插入到左边的合适的位置
 */
@SuppressWarnings("all")
public class InsertSort {

    public static void main(String[] args) {
        int[] array = new int[]{5, 9, 1, 6, 3, 2, 7};
        InsertSort.method01(array);
        InsertSort.print(array);
    }

    public static void method01(int[] array) {
        int length = array.length;

        for (int i = 1; i < length; i++) {
            // 需要插入的元素
            int insertNum = array[i];
            int j = i;
            for (; j > 0; j--) {
                // 往后挪
                if (array[j - 1] > insertNum) {
                    array[j] = array[j - 1];
                } else {
                    break;
                }
            }
            array[j] = insertNum;
        }
    }

    public static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
