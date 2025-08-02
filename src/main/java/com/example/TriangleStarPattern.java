// Write Java program to print a triangle star pattern.

package com.example;

public class TriangleStarPattern {
    public static void main(String[] args) {
        int n = 5; // Number of rows for the triangle pattern
        printTriangleStarPattern(n);
    }

    private static void printTriangleStarPattern(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = n - i; j > 1; j--) {
                System.out.print(" ");
            }

            for (int k = 0; k <= i; k++) {
                System.out.print("* ");
            }
            // Move to the next line after each row
            System.out.println();
        }
    }
}
