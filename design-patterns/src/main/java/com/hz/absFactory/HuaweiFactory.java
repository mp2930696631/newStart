package com.hz.absFactory;

/**
 * @author zehua
 * @date 2020/11/14 21:15
 */
public class HuaweiFactory extends AbsFactory {
    @Override
    public PhoneProduct getPhone() {
        return new HuaweiPhone();
    }

    @Override
    public RouterProduct getRouter() {
        return new HuaweiRouter();
    }
}
