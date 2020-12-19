package com.hz.objectLayout;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author zehua
 * @date 2020/12/19 16:59
 */
public class ObjectLayout {

    public static void getObjLayout(Object obj) {
        ClassLayout layout = ClassLayout.parseInstance(obj);
        System.out.println(layout.toPrintable());
    }
}
