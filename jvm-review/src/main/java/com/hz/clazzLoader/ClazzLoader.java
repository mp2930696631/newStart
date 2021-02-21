package com.hz.clazzLoader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author zehua
 * @date 2020/12/17 20:18
 */
public class ClazzLoader extends ClassLoader {
    private String baseDir = "F:/zehua/test/classLoader/";

    public ClazzLoader() {

    }

    public ClazzLoader(String baseDir) {
        this.baseDir = baseDir;
    }


    public Class<?> loadClass(File file) throws ClassNotFoundException {
        final String absolutePath = file.getAbsolutePath();
        final int i = absolutePath.lastIndexOf("com\\hz");
        String packageName = absolutePath.substring(i, absolutePath.length()-6).replace("\\", ".");
        return loadClass(packageName);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        final String filePath = getFilePath(name);
        final byte[] classBytes = getClassBytes(filePath);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    public Class<?> findClass(File file) throws ClassNotFoundException {
        final String absolutePath = file.getAbsolutePath();
        final int i = absolutePath.lastIndexOf("com\\hz");
        String packageName = absolutePath.substring(i, absolutePath.length()-6).replace("\\", ".");
        return findClass(packageName);
    }

    private String getFilePath(String name) {
        String classFilePath = name.replace(".", "/") + ".class";
        return baseDir + classFilePath;
    }

    private byte[] getClassBytes(String file) {
        byte[] result = null;
        try (
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            byte[] buffer = new byte[8];
            int offset = 0;
            while ((offset = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, offset);
            }

            result = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
