package com.hz.factory.factoryMethod;

/**
 * 每一个工厂对应一种产品
 *
 * @author zehua
 * @date 2020/11/14 21:01
 */
public class Main {

    public static void main(String[] args) {
        CarFactory carFactory = new LamFactory();
        Car car = carFactory.getCar();
        System.out.println(car);
    }

}
