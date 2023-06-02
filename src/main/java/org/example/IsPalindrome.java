package org.example;

public class IsPalindrome {
    public static boolean isPalindrome(String str, int start, int end) {
        if (str == null || str.isEmpty() || start < 0 || end < 0 || end >= str.length() || end < start) {
            throw new IllegalArgumentException("Invalid input parameters");
        }
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
