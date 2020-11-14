package com.hz.absFactory;

/**
 * 抽象工厂强调产品一簇
 *
 * @author zehua
 * @date 2020/11/14 21:17
 */
public class Main {

    public static void main(String[] args) {
        AbsFactory factory = new HuaweiFactory();
        PhoneProduct phone = factory.getPhone();
        RouterProduct router = factory.getRouter();
        System.out.println(phone);
        System.out.println(router);
    }

}
