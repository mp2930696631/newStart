package com.zehua.testIO;

import java.io.File;

public class GetAllFiles {
    public static int count = 0;

    public static void main(String[] args) {
        String path = "D:\\User\\Softwares\\Idea\\Workspace\\newStart\\java2ee";
        File file = new File(path);

        new GetAllFiles().getFiles(file);
        System.out.println(count);
    }

    private void getFiles(File file) {
        if (file.isFile()) {
            System.out.println(file);
            count++;
            return;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            getFiles(f);
        }
    }

}
