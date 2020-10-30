package com.zehua.review.lambda;

import com.zehua.review.ReviewUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auther zehua
 * @Date 2020/10/30 19:57
 */
public class StreamLambda {

    public static void main(String[] args) {
        streamAPI();
    }

    public static void generateStream() {
        // stream的生成
        // 1、数组
        // int[] ints = new int[]{1, 2, 3, 4, 5};
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        integerStream.forEach(System.out::println);
        // 2、集合
        // Arrays.asList(1,2,3,4,5);
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        Stream<Integer> listStream = integerList.stream();
        System.out.println(listStream.findFirst().get());
        // Stream.generate(Supplier)
        Stream<Integer> generateStream = Stream.generate(() -> 1);
        generateStream.limit(10).forEach(System.out::println);
        // Stream.iterate(seed, [predicate], unaryOperator)
        Stream<Integer> iterateStream = Stream.iterate(1, x -> x + 2);
        iterateStream.limit(5).forEach(System.out::println);
        Stream<Integer> integerStream2 = Stream.iterate(1, x -> x < 20, x -> x + 2);
        integerStream2.forEach(System.out::println);
        // other
        String str = "abcdefg";
        IntStream intStream = str.chars();
        intStream.mapToObj(x -> (char) x).forEach(System.out::println);
    }

    public static void streamAPI() {
        ArrayList<Student> students = ReviewUtils.generateStudents();
        Stream<Student> studentStream = students.stream();
        // 筛选
        studentStream.filter(stud -> stud.getGrade() > 70).forEach(System.out::println);
        //去重
        Stream<Integer> integerStream = Stream.of(1, 1, 2, 2, 5, 5, 8, 8, 1, 7);
        integerStream.distinct().forEach(System.out::println);
        // 最大值
        System.out.println("-----------------------------------");
        Stream<Integer> integerStream2 = Stream.of(1, 1, 2, 2, 5, 5, 8, 8, 1, 7);
        System.out.println(integerStream2.distinct().mapToInt(x -> x).max().getAsInt());
        // 最大值
        Stream<Student> studentStream2 = students.stream();
        System.out.println(studentStream2.max((stu1, stu2) -> stu1.getGrade() - stu2.getGrade()).get());
        // 求和
        Stream<Integer> integerStream3 = Stream.of(1, 1, 2, 2, 5, 5, 8, 8, 1, 7);
        Stream<Integer> integerStream4 = Stream.of(1, 1, 2, 2, 5, 5, 8, 8, 1, 7);
        Stream<Integer> integerStream5 = Stream.of(1, 1, 2, 2, 5, 5, 8, 8, 1, 7);
        System.out.println(integerStream3.mapToInt(x -> x).sum());
        System.out.println(integerStream4.reduce((x, y) -> x + y).get());
        System.out.println(integerStream5.reduce(2, (x, y) -> x + y));
        // 返回一个ArrayList
        System.out.println("----------------------------------");
        Stream<Student> studentStream3 = students.stream();
        studentStream3.map(student -> student.toString()).collect(ArrayList::new, (list, str) -> list.add(str), (l1, l3) -> l1.addAll(l3)).stream().forEach(System.out::println);

        // students.stream().collect(Collectors.toList());
        Stream d = Stream.of(new Integer[]{1,2});
    }

}
