package com.zehua.review.io;

import java.io.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 12:07
 */
public class PrintStreamToFileFromConsoleTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String originFileFullName = path + File.separator + originFileName;
        File file = new File(originFileFullName);
        try (
                Reader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                FileWriter fw = new FileWriter(file);
        ) {
            String str = "";
            while (!str.equals("exit")) {
                str = br.readLine();
                fw.write(str+"\n");
                fw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
