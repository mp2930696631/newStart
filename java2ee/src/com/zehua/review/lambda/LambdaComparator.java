package com.zehua.review.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 1、使用了Random类产生10个[70, 101)的int随机数
 * 2、使用函数式接口Comparator
 * 3、方法引用
 * @Auther zehua
 * @Date 2020/10/30 17:14
 */
public class LambdaComparator {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            IntStream intStream = random.ints(60, 101);
            int grade = intStream.findFirst().getAsInt();
            students.add(new Student("zehua", grade));
        }

        Comparator<Student> comparator = (s1, s2) -> s1.getGrade() - s2.getGrade();
        Collections.sort(students, comparator);

        students.stream().forEach(System.out::println);
    }

}
