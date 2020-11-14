package com.hz.factory.factoryMethod;

/**
 * @author zehua
 * @date 2020/11/14 21:00
 */
public class LamFactory extends CarFactory {
    @Override
    public Car getCar() {
        return new Lamborghini();
    }
}
