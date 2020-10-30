package com.zehua.review.io;

import java.io.*;
import java.util.Scanner;

public class PrintStreamTest {

    public static void main(String[] args) {
        try (
                Reader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                Writer osw = new OutputStreamWriter(System.out);
                BufferedWriter bw = new BufferedWriter(osw);
        ) {
            String str = "";
            while (!str.equals("exit")) {
                str = br.readLine();
                bw.write(str);
                // 注意一定要加flush
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
