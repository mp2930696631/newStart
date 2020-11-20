package com.zehua.testThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zehua
 * @date 2020/11/20 21:48
 */
public class DateUtil {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal;

    static {
        simpleDateFormatThreadLocal = new ThreadLocal<>() {
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };
    }

    public static Date getFormatedDate(String str) throws ParseException {
        return simpleDateFormatThreadLocal.get().parse(str);
    }

    // 此情况下，parse会有问题，有时还会报错
    // 当然，也可以吧simpleDateFormat移至线程内部，变成局部变量，这样就不会有问题
    public static void testSimpleDateFormatWithout() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        final String s = simpleDateFormat.format(date);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(simpleDateFormat.parse(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    // 使用threadLocal
    public static void testSimpleDateFormatWith() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        final String s = simpleDateFormat.format(date);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(getFormatedDate(s));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
