package com.hz.factory.simpleFactory;

/**
 * 简单工厂又叫静态工厂
 * 因为是通过调用工厂的静态方法来获取实例
 *
 * @author zehua
 * @date 2020/11/14 20:46
 */
public class Main {

    public static void main(String[] args) {
        Car lamborghini = SimpleFactory.getLamCar();
        Car daZhongCar = SimpleFactory.getDaZhong();
        System.out.println(lamborghini);
        System.out.println(daZhongCar);
    }

}
