package com.hz.classFiles;

import java.io.File;
import java.util.ArrayList;

/**
 * @author zehua
 * @date 2021/2/21 10:55
 */
public class Main {

    public static void main(String[] args) {
        final ArrayList<File> allClassFiles = ClassFiles.getAllClassFiles("F:\\zehua\\test\\helloworld");
        for (File allClassFile : allClassFiles) {
            System.out.println(allClassFile);
        }
    }

}
