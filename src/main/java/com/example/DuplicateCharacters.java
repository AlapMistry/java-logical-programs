// Write Java program to check duplicate characters in a string.

package com.example;

public class DuplicateCharacters {
    public static void main(String[] args) {
        String str = "mynameisalap";
        int count = 0;
        char[] chars = str.toCharArray();
        int stringLength = str.length();
        for (int i = 0; i < stringLength; i++) {
            for (int j = i + 1; j < stringLength; j++) {
                if (chars[i] == chars[j]) {
                    System.out.println(chars[j]);
                    count++;
                    break;
                }
            }
        }
    }
}
