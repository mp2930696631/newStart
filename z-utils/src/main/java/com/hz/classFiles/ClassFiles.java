package com.hz.classFiles;

import java.io.File;
import java.util.ArrayList;

/**
 * @author zehua
 * @date 2021/2/21 10:45
 */
public class ClassFiles {

    /**
     * 获取某目录下的所有class文件
     *
     * @param path
     * @return
     */
    public static ArrayList<File> getAllClassFiles(String path) {
        ArrayList<File> al = new ArrayList<>();
        File file = new File(path);
        recursion(file, al);
        return al;
    }

    private static void recursion(File file, ArrayList<File> al) {
        if (file.isFile()) {
            final String absolutePath = file.getAbsolutePath();
            final String substring = absolutePath.substring(absolutePath.lastIndexOf(".") + 1);
            if (substring.equals("class")) {
                al.add(file);
            }
        } else {
            final File[] files = file.listFiles();
            for (File file1 : files) {
                recursion(file1, al);
            }
        }
    }

}
