package com.zehua.review.lambda;

import java.util.function.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 17:36
 */
public class LambdaMethodRef {

    public static void main(String[] args) {
        // 静态方法引用
        System.out.println("----------------静态方法引用-----------------");
        Consumer<Integer> staticCon = MethodRef::methodConsumer;
        staticCon.accept(40);

        Supplier<Student> staticSup = MethodRef::methodSupplier;
        System.out.println(staticSup.get());

        BiConsumer<String, Integer> staticBiCon = MethodRef::methodBiConsumer;
        staticBiCon.accept("zehua1", 50);

        Function<Integer, Student> staticFun = MethodRef::methodFunction;
        System.out.println(staticFun.apply(60));

        UnaryOperator<Student> staticUO = MethodRef::methodUnaryOperator;
        System.out.println(staticUO.apply(new Student("zehua2", 70)));

        BiFunction<String, Integer, Student> staticBiFun = MethodRef::methodBiFunction;
        System.out.println(staticBiFun.apply("zehua3", 80));

        BinaryOperator<Student> staticBO = MethodRef::methodBinaryOperator;
        System.out.println(staticBO.apply(new Student("zehua4", 90), new Student("zehua5", 100)));

        // 实例方法引用
        System.out.println("----------------实例方法引用-----------------");
        MethodRef instanceMethodRef = new MethodRef();
        Function<Integer, Student> instanceFun = instanceMethodRef::instanceMethodRef;
        System.out.println(instanceFun.apply(80));

        // 对象方法引用
        System.out.println("----------------对象方法引用-----------------");
        // 下面的代码等价于objectBiFun = (methodRef, integer)->methodRef.instanceMethodRef(integer)（注意：并非new NethodRef().instanceMethodRef(integer)）
        // 对应的函数式接口中的抽象方法为：Student xxMethodName(methodRef, integer);
        // 注意，对象方法引用的条件
        // 1、抽象方法的第一个参数是实例方法的类型---methodRef(抽象方法第一个参数)，instanceMethodRef(实例方法)
        // 2、抽象方法的剩余参数是实例方法的参数 抽象方法的剩余参数(integer)，instanceMethodRef实例方法的参数（integer）
        MethodRef objectMethodRef = new MethodRef();
        BiFunction<MethodRef, Integer, Student> objectBiFun = MethodRef::instanceMethodRef;
        // 等价于下面这种写法
        BiFunction<MethodRef, Integer, Student> objectBiFun2 = (methodRef, in) -> methodRef.instanceMethodRef(in);
        System.out.println(objectBiFun.apply(objectMethodRef, 90));
        System.out.println(objectMethodRef);

        // 构造方法引用
        System.out.println("----------------构造方法引用-----------------");
        Supplier<MethodRef> makeSupp = MethodRef::new;
        // 等价于下面这种写法
        Supplier<MethodRef> makeSupp2 = () -> new MethodRef();
        MethodRef makeMethodRef = makeSupp.get();
        System.out.println(makeMethodRef.instanceMethodRef(80));

        Function<Student, MethodRef> makeFun = MethodRef::new;
        MethodRef makeMethodRef2 = makeFun.apply(new Student("zehua", 80));
        makeMethodRef2.instanceMethodRef(80);
    }

}

class MethodRef {
    private Student student;

    public MethodRef() {
    }

    public MethodRef(Student student) {
        this.student = student;
    }

    public Student instanceMethodRef(int grade) {
        System.out.println(this);
        return new Student("instanceMethodRef", grade);
    }

    public static void methodConsumer(int grade) {
        System.out.println(new Student("methodConsumer", grade));
    }

    public static Student methodSupplier() {

        return new Student("methodSupplier", 100);
    }

    public static void methodBiConsumer(String str, int grade) {
        System.out.print("methodBiConsumer--");
        System.out.println(new Student(str, grade));
    }

    public static Student methodFunction(int grade) {
        return new Student("methodFunction", grade);
    }

    public static Student methodUnaryOperator(Student stu) {

        return new Student("methodUnaryOperator", stu.getGrade());
    }

    public static Student methodBiFunction(String str, int grade) {
        System.out.println("methodBiFunction----");
        return new Student(str, grade);
    }

    public static Student methodBinaryOperator(Student stu1, Student stu2) {
        System.out.println("methodBinaryOperator---");
        return new Student(stu1.getName(), stu2.getGrade());
    }
}
