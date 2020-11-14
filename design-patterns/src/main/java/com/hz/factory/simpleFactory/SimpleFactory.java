package com.hz.factory.simpleFactory;

/**
 * 简单工厂又叫静态工厂
 * 因为是通过调用工厂的静态方法来获取实例
 *
 * @author zehua
 * @date 2020/11/14 20:46
 */
public class SimpleFactory {
    public static Car getLamCar() {
        return new Lamborghini();
    }

    public static Car getDaZhong() {
        return new DaZhongCar();
    }
}
