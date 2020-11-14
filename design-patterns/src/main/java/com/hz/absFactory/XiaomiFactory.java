package com.hz.absFactory;

/**
 * @author zehua
 * @date 2020/11/14 21:16
 */
public class XiaomiFactory extends AbsFactory {
    @Override
    public PhoneProduct getPhone() {
        return new XiaomiPhone();
    }

    @Override
    public RouterProduct getRouter() {
        return new XiaomiRouter();
    }
}
