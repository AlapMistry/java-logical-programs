/*
Write Java program that reads a given string and outputs the most frequently occurring word(s).
Example:
Input: "Hello world! This is a test. Hello world again." 
Output: "Hello" (occurs 2 times)
*/

package com.example;

import java.util.*;

public class DuplicateWords {
    public static void main(String[] args) {
        String input = "Hello world! This is a test. Hello world again.";

        String[] words = input.split("\\s+");

        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }

        int max = 0;
        for (int count : freq.values()) {
            if (count > max) max = count;
        }

        List<String> mostFrequent = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == max) {
                mostFrequent.add(entry.getKey());
            }
        }

        System.out.println("Most frequent word(s): " + mostFrequent + " (occurs " + max + " times)");
    }
}
