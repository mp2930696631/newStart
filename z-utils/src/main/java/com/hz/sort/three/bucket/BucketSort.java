package com.hz.sort.three.bucket;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 桶排序：可以对浮点型进行排序
 *
 * @author zehua
 * @date 2021/3/8 9:30
 * <p>
 * 计算下标的算法：让最大值单独放一个在一个桶中
 * (int) ((bucketCount - 1) * (array[i] - min) / (max - min))
 */
@SuppressWarnings("all")
public class BucketSort {
    private static final int bucketCount = 6;
    private static final ArrayList<Double>[] als = new ArrayList[bucketCount];

    static {
        for (int i = 0; i < bucketCount; i++) {
            als[i] = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        double[] array = new double[]{5.41, 3.14, 9.26, 10.0, 1.3, 8.7, 8.555, 6.784, 3.78, 3.0};
        method01(array);
        print(array);
    }

    // 桶内的取值为：左闭右开，最大值单独一个桶
    public static void method01(double[] array) {
        final double[] maxMin = getMaxMin(array);
        double min = maxMin[0];
        double max = maxMin[1];

        for (int i = 0; i < array.length; i++) {
            // 计算下标的算法
            int index = (int) ((bucketCount - 1) * (array[i] - min) / (max - min));
            als[index].add(array[i]);
        }

        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(als[i]);
        }

        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            ArrayList al = als[i];
            for (int j = 0; j < al.size(); j++) {
                array[index++] = (Double) al.get(j);
            }
        }
    }

    private static double[] getMaxMin(double[] array) {
        double max = array[0];
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }

            if (array[i] < min) {
                min = array[i];
            }
        }

        double[] result = new double[]{min, max};

        return result;
    }

    public static void print(double[] array) {
        for (double i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
