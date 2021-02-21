package com.hz.v4.other;

import net.sf.cglib.core.DefaultNamingPolicy;

/**
 * @author zehua
 * @date 2021/2/18 13:27
 */
public class MyNamePolicy extends DefaultNamingPolicy {

    @Override
    protected String getTag() {
        return "byHZ";
    }
}
