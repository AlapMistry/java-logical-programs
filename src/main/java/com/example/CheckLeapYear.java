// Write Java program to check if a given year is a leap year or not.

package com.example;

public class CheckLeapYear {
    public static void main(String[] args) {
        isLeapYear(2032);
    }

    private static void isLeapYear(int year) {
        boolean isLeap = false;
        if ((year % 4) == 0) {
            if ((year % 100) == 0) {
                if ((year % 400) == 0) {
                    isLeap = true;
                } else {
                    isLeap = false;
                }
            } else {
                isLeap = true;
            }
        } else {
            isLeap = false;
        }

        if (isLeap) {
            System.out.println(year + " is leap year.");
        } else  {
            System.out.println(year + " is not leap year.");
        }
    }
}
