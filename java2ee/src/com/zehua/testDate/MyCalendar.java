package com.zehua.testDate;

import java.util.*;

public class MyCalendar {
    private static final int MAX_DAYS = 35;
    private static final int[] weekIndexes = {5, 0, 1, 2, 3, 4, 6};
    private static final Map<Integer, Integer> monthDayMap;

    static {
        monthDayMap = new HashMap<>();
        monthDayMap.put(1, 31);
        monthDayMap.put(3, 31);
        monthDayMap.put(4, 30);
        monthDayMap.put(5, 31);
        monthDayMap.put(6, 30);
        monthDayMap.put(7, 31);
        monthDayMap.put(8, 31);
        monthDayMap.put(9, 30);
        monthDayMap.put(10, 31);
        monthDayMap.put(11, 30);
        monthDayMap.put(12, 31);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int year = scan.nextInt();
        int month = scan.nextInt();
        int day = scan.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int[] days = new int[MyCalendar.MAX_DAYS];
        int weekIndex = weekIndexes[dayOfWeek - 1];
        int daysInMonth = 0;
        if (month == 2) {
            if (isLeapYear(year)) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        } else {
            daysInMonth = monthDayMap.get(month);
        }

        int count = weekIndex + daysInMonth;
        for (int i = weekIndex; i < count; i++) {
            days[i] = i - weekIndex + 1;
        }

        print(days, count, day);
    }

    private static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }

        return false;
    }

    private static void print(int[] array, int length, int day) {
        System.out.println("日   一   二   三   四   五   六");
        for (int i = 0; i < length; i++) {
            if (array[i] == 0) {
                System.out.print("\t");
                continue;
            } else {
                System.out.print(array[i]);
                if (array[i] == day) {
                    System.out.print("*  ");
                } else {
                    System.out.print("   ");
                }
            }
            if ((i + 1) % 7 == 0) {
                System.out.println();
            }
        }
    }

}
