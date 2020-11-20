package com.zehua.testThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zehua
 * @date 2020/11/20 21:48
 */
public class Main {

    public static void main(String[] args) throws ParseException {

        // without threadlocal
        // DateUtil.testSimpleDateFormatWithout();

        // with threadlocal

        DateUtil.testSimpleDateFormatWith();

    }

}
