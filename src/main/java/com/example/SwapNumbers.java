// Write Java code to swap two numbers without using a temporary variable

package com.example;

public class SwapNumbers {
    public static void main(String[] args) {
        int a = 10, b = 20;
        System.out.println("Before swapping: a = " + a + ", b = " + b);
        swapNumbers(a, b);
    }

    private static void swapNumbers(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }
}
