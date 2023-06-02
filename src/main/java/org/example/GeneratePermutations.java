package org.example;

import java.util.ArrayList;
import java.util.List;
public class GeneratePermutations {
    public static List<String> generatePermutations(String str, int length) {
        // Specification: Given a string str and an integer length,
        // generate all possible permutations of length characters from str
        // and return them as a list of strings.
        if(str == null){
            throw new IllegalArgumentException("str can't be null!");
        }else if(length < 0){
            throw new IllegalArgumentException("length can't be negative!");
        }
        List<String> result = new ArrayList<>();
        generatePermutationsHelper(str, length, "", result);
        return result;
    }
    private static void generatePermutationsHelper(String str, int length, String current, List<String> result) {
        if (current.length() == length) {
            result.add(current);
        } else {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                generatePermutationsHelper(str, length, current + c, result);
            }
        }
    }
}