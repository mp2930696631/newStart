package com.hz.objectSize;

import java.lang.instrument.Instrumentation;

/**
 *1、先按下面premain方法上面的注释操作
 * 2、请看README.md文件
 * @author zehua
 * @date 2020/12/16 3:59
 */
public class ObjectSizeAgent {

    private static Instrumentation inst;

    // jvm 会在执行main方法之前执行这个方法
    // 前提是需要在src/META-INF/MANIFAST.MF文件执行配置
    // Premain-Class: com.hz.objectSize.ObjectSizeAgent(注意MANIFAST.MF需要最后带上一个空行)
    public static void premain(String agentArgs, Instrumentation _inst) {
        inst = _inst;
    }

    public static long sizeOf(Object o) {
        return inst.getObjectSize(o);
    }

}
