package com.hz.tomcatClassLoader.classLoaders;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zehua
 * @date 2021/2/21 10:40
 */
public class ClassLoaderHolder {
    private static Map<String, ClassLoader> cls = new HashMap<>();

    public static ClassLoader getClassLoader(String clName) {
        return cls.get(clName);
    }

    public static void addClassLoader(String clName, ClassLoader classLoader) {
        cls.put(clName, classLoader);
    }

}
